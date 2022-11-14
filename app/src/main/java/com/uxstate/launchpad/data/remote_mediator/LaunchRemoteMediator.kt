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
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class LaunchRemoteMediator @Inject constructor(
    private val db: LaunchDatabase,
    private val api: LaunchAPI
) : RemoteMediator<Int, Launch>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Launch>
    ): MediatorResult {

        // obtain daos
        val launchDao = db.launchDao
        val remoteKeysDao = db.remoteKeysDao

        return try {

            // Determine which page to load depending on the supplied LoadType
            val loadKey = when (loadType) {

                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {

                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {

                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    lastItem.id
                }
            }

            val response = api.getPreviousLaunches(offSet = loadKey.plus(10))

            db.withTransaction {

                if (loadType == LoadType.REFRESH) {

                    launchDao.clearLaunches()
                }
                launchDao.insertLaunches(response.launchDTOS.map { it.toEntity() })

                MediatorResult.Success(endOfPaginationReached = response.launchDTOS.isEmpty())
            }
        } catch (e: IOException) {

            MediatorResult.Error(e)
        } catch (e: HttpException) {

            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return super.initialize()
    }
}