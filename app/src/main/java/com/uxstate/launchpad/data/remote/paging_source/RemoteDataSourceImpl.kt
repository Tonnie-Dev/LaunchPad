package com.uxstate.launchpad.data.remote.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.data.remote_mediator.LaunchRemoteMediator
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.paging_source.RemoteDataSource
import com.uxstate.launchpad.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: LaunchAPI,
    private val db: LaunchDatabase
) : RemoteDataSource {

    private val dao = db.launchDao

    @OptIn(ExperimentalPagingApi::class)
    override fun getLaunches(): Flow<PagingData<Launch>> {

        val pagingSourceFactory = { dao.getLaunches() }


        return Pager(
                config = PagingConfig(pageSize = Constants.ITEMS_PER_PAGE_LIMIT),
                remoteMediator = LaunchRemoteMediator(db = db, api = api),
                pagingSourceFactory =pagingSourceFactory
        ).flow

    }
}