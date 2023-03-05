package com.uxstate.launchpad.data.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.data.mapper.toPrevEntity
import com.uxstate.launchpad.data.remote.LaunchApi
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.utils.Constants
import com.uxstate.launchpad.utils.Constants.CACHE_TIMEOUT
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
class PrevsLaunchMediator @Inject constructor(
    private val db: LaunchDatabase,
    private val api: LaunchApi
) : RemoteMediator<Int, Launch>() {

    private val dao = db.launchDao

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

                    val lastPrevLaunch = dao.selectLastPrevLaunch()
                    val lastItem = state.lastItemOrNull()
                    Timber.i("Hitting Append with ${lastItem?.id ?: "NULL"}")
                    if (lastPrevLaunch?.id == null) {

                        Timber.i("Hit Append - End Reached!")
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    Timber.i("Hitting Append with ${lastPrevLaunch.id}")
                    lastPrevLaunch.id
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

        val timeNow = System.currentTimeMillis()
        val lastUpdated = dao.selectFirstPrevLaunch()?.timeStamp ?: 0
        val timeDifference = (timeNow - lastUpdated) / 1000 / 60

        return if (timeDifference.toInt() >= CACHE_TIMEOUT) {
            Timber.i("Initialize() - LAUNCH_INITIAL_REFRESH")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {

            Timber.i("Initialize() - SKIP_INITIAL_REFRESH")
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }
}
