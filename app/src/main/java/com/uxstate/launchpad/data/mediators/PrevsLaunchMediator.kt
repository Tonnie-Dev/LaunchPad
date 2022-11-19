package com.uxstate.launchpad.data.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.data.mapper.toPrevEntity
import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.util.Constants
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
class PrevsLaunchMediator @Inject constructor(
    private val db: LaunchDatabase,
    private val api: LaunchAPI
) : RemoteMediator<Int, Launch>() {

    private val dao = db.dao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Launch>
    ): MediatorResult {

        Timber.i("Inside load() of the Remote Mediator")

        return try {

            // Determine which page to load depending on the supplied LoadType
            val loadKey = when (loadType) {

                LoadType.REFRESH -> {
                    Timber.i("Hitting Refresh loadkey should be 0")
                    Constants.OFFSET_STARTING_INDEX
                }

                LoadType.PREPEND -> {
                    Timber.i("PrePend!!")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {

                    val lastItem = state.lastItemOrNull()
                    Timber.i("Hitting Append with ${lastItem?.id ?: "NULL"}")
                    if (lastItem == null) {

                        Timber.i("Hit Append - End Reached!")
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    Timber.i("Hitting Append with ${lastItem.id}")
                    lastItem.id
                }
            }

            Timber.i("loadKey is: $loadKey")
            val response = api.getPreviousLaunches(offSet = loadKey)

            Timber.i("The size is: ${response.launchDTOS.size}")
            if (response.launchDTOS.isNotEmpty()) {
                db.withTransaction {

                    if (loadType == LoadType.REFRESH) {

                        dao.clearPreviousLaunches()
                        Timber.i("REFRESH - Data cleared")
                    }

                    Timber.i("NOT REFRESH - Data Inserted")
                    dao.insertPreviousLaunches(response.launchDTOS.map { it.toPrevEntity() })
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.next == null)
        } catch (e: IOException) {
            Timber.i("IO Error in RemoteMediator: $e")
            e.printStackTrace()
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Timber.i("Http Error in RemoteMediator: $e")
            e.printStackTrace()
            MediatorResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.i("Other Errors in RemoteMediator: $e")
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {

        Timber.i("Initialize Call Detected")
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
}