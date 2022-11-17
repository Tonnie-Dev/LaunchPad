package com.uxstate.launchpad.data.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.data.mapper.toUpsEntity
import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.util.Constants
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
class UpsLaunchMediator @Inject constructor(
    private val db: LaunchDatabase,
    private val api: LaunchAPI
) : RemoteMediator<Int, Launch>() {

    private val dao = db.launchDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Launch>
    ): MediatorResult {

        return try {

            Timber.i("load() called with state:  ${state.lastItemOrNull()}")
            val loadKey = when (loadType) {

                LoadType.REFRESH -> {

                    Timber.i("Inside REFRESH Block")
                    Constants.OFFSET_STARTING_INDEX
                }
                LoadType.PREPEND -> {
                    Timber.i("Inside PREPEND Block")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    Timber.i("Inside APPEND Block")
                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {

                        Timber.i("END REACHED!!")
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    Timber.i("Returned ${lastItem.id} as ke")
                    lastItem.id
                }
            }

            Timber.i("Load Key is $loadKey") // Make API request
            val response = api.getUpcomingLaunches(offSet = loadKey)

            db.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    dao.clearUpcomingLaunches()
                    Timber.i("Existing Data Wiped")
                }
                Timber.i("New Data in place")
                dao.insertUpcomingLaunches(response.launchDTOS.map { it.toUpsEntity() })
            }

            MediatorResult.Success(endOfPaginationReached = response.next == null)
        } catch (e: HttpException) {
            e.printStackTrace()
            Timber.i("Http Error: $e")
            MediatorResult.Error(e)
        } catch (e: IOException) {
            e.printStackTrace()
            Timber.i("IO Error: $e")
            MediatorResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()

            Timber.i("Other errors: $e")
            MediatorResult.Error(e)
        }
    }
}