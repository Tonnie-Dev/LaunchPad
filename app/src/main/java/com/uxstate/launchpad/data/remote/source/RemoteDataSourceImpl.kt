package com.uxstate.launchpad.data.remote.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.uxstate.launchpad.data.local.dao.LaunchDao
import com.uxstate.launchpad.data.mediators.PrevsLaunchMediator
import com.uxstate.launchpad.data.mediators.UpsLaunchMediator
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.pagingSource.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl
    @Inject
    constructor(
        private val dao: LaunchDao,
        private val prevsLaunchMediator: PrevsLaunchMediator,
        private val upsLaunchMediator: UpsLaunchMediator,
    ) : RemoteDataSource {
        companion object {
            const val DEFAULT_PAGE_SIZE = 3
        }

        override fun getPreviousLaunches(): Flow<PagingData<Launch>> {
            return Pager(
                config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
                remoteMediator = prevsLaunchMediator,
                pagingSourceFactory = dao::getPreviousLaunches,
            ).flow
        }

        override fun getUpcomingLaunches(): Flow<PagingData<Launch>> {
            return Pager(
                config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
                remoteMediator = upsLaunchMediator,
                pagingSourceFactory = dao::getUpcomingLaunches,
            ).flow
        }
    }
