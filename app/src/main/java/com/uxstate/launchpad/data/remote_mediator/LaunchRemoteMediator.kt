package com.uxstate.launchpad.data.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.domain.model.Launch
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class LaunchRemoteMediator @Inject constructor(
    private val db: LaunchDatabase,
    private val api: LaunchAPI
): RemoteMediator<String, Launch>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<String, Launch>
    ): MediatorResult {
        TODO("Not yet implemented")
    }

    override suspend fun initialize(): InitializeAction {
        return super.initialize()
    }
}