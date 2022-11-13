package com.uxstate.launchpad.data.remote.paging_source

import androidx.paging.PagingData
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.paging_source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: LaunchAPI,
    private val db: LaunchDatabase
) : RemoteDataSource {
    override fun getLaunches(): Flow<PagingData<Launch>> {
        TODO("Not yet implemented")
    }
}