package com.uxstate.launchpad.data.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.uxstate.launchpad.data.local.dao.LaunchDao
import com.uxstate.launchpad.data.local.database.utils.DatabaseTransactionProvider
import com.uxstate.launchpad.data.mapper.toUpsEntity
import com.uxstate.launchpad.data.remote.api.LaunchApi
import com.uxstate.launchpad.data.remote.api.constants.LaunchApiParams
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.utils.Constants.CACHE_TIMEOUT
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class UpsLaunchMediator
    @Inject
    constructor(
        private val dao: LaunchDao,
        private val transactionProvider: DatabaseTransactionProvider,
        private val api: LaunchApi,
    ) : RemoteMediator<Int, Launch>() {
        override suspend fun load(
            loadType: LoadType,
            state: PagingState<Int, Launch>,
        ): MediatorResult {
            Timber.i("load() called with state:  ${state.lastItemOrNull()}")
            return try {
                val loadKey =
                    when (loadType) {
                        LoadType.REFRESH -> {
                            Timber.i("Inside REFRESH Block")
                            LaunchApiParams.DEFAULT_OFFSET
                        }
                        LoadType.PREPEND -> {
                            Timber.i("Inside PREPEND Block")
                            return MediatorResult.Success(endOfPaginationReached = true)
                        }

                        LoadType.APPEND -> {
                            Timber.i("Inside APPEND Block")
                            // val lastItem = state.lastItemOrNull()
                            val lastId = dao.selectLastUpLaunch()?.id

                            if (lastId == null) {
                                Timber.i("END REACHED!!")
                                return MediatorResult.Success(endOfPaginationReached = true)
                            }
                            // Timber.i("Returned ${lastItem.id} as key")
                            lastId
                        }
                    }

                Timber.i("Load Key is $loadKey") // Make API request
                val response = api.getUpcomingLaunches(offset = loadKey)
                Timber.i("The size of response is: ${response.launchDtos.map { it.name }}")

                if (response.launchDtos.isNotEmpty()) {
                    transactionProvider.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            dao.clearUpcomingLaunches()
                            Timber.i("Existing Data Wiped")
                        }
                        Timber.i("New Data in place")
                        dao.insertUpcomingLaunches(response.launchDtos.map { it.toUpsEntity() })
                    }
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

        override suspend fun initialize(): InitializeAction {
            val timeNow = System.currentTimeMillis()
            val lastUpdate = dao.selectFirstPrevLaunch()?.timeStamp ?: 0L
            val timeDifference = (timeNow - lastUpdate) / 1000 / 60

            return if (timeDifference >= CACHE_TIMEOUT) {
                Timber.i("Initialize() - LAUNCH_INITIAL_REFRESH")
                InitializeAction.LAUNCH_INITIAL_REFRESH
            } else {
                Timber.i("Initialize() - LAUNCH_INITIAL_REFRESH")
                InitializeAction.SKIP_INITIAL_REFRESH
            }
        }
    }
