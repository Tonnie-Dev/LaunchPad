package com.uxstate.launchpad.data.remote.data_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.data.mediators.PrevsLaunchMediator
import com.uxstate.launchpad.data.mediators.UpsLaunchMediator
import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.paging_source.RemoteDataSource
import com.uxstate.launchpad.util.Constants
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl @Inject constructor(
    private val api: LaunchAPI,
    private val db: LaunchDatabase
) : RemoteDataSource {

    private val dao = db.launchDao

    override fun getPreviousLaunches(): Flow<PagingData<Launch>> {

        val pagingSourceFactory = { dao.getPreviousLaunches() }

        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE
            ),
            remoteMediator = PrevsLaunchMediator(db = db, api = api),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun getUpcomingLaunches(): Flow<PagingData<Launch>> {
        val pagingSourceFactory = { dao.getUpcomingLaunches() }

        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = UpsLaunchMediator(db = db, api = api)
        ).flow
    }
}