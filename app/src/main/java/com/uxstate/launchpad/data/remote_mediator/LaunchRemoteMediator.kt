package com.uxstate.launchpad.data.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.data.mapper.toEntity
import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.util.Constants
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
class LaunchRemoteMediator @Inject constructor(
    private val db: LaunchDatabase,
    private val api: LaunchAPI
) : RemoteMediator<Int, Launch>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Launch>
    ): MediatorResult {
        Timber.i("Inside load() of the Remote Mediator")
        // obtain daos
        val launchDao = db.launchDao

        return try {

            // Determine which page to load depending on the supplied LoadType
            val loadKey = when (loadType) {

                LoadType.REFRESH -> Constants.OFFSET_STARTING_INDEX
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {

                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {

                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    lastItem.id
                }
            }

            Timber.i("loadKey is: $loadKey")
            val response = api.getPreviousLaunches(offSet = loadKey)

            db.withTransaction {

                if (loadType == LoadType.REFRESH) {

                    launchDao.clearLaunches()
                }
                launchDao.insertLaunches(response.launchDTOS.map { it.toEntity() })

                MediatorResult.Success(endOfPaginationReached = response.launchDTOS.isEmpty())
            }
        } catch (e: IOException) {
            Timber.i("IO Error in RemoteMediator: $e")
            e.printStackTrace()
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Timber.i("Http Error in RemoteMediator: $e")
            e.printStackTrace()
            MediatorResult.Error(e)
        } catch (e: Exception) {

            Timber.i("Other Errors in RemoteMediator: $e")
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }
}