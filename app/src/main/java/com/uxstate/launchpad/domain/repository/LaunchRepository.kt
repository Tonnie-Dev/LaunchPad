package com.uxstate.launchpad.domain.repository

import androidx.paging.PagingData
import com.uxstate.launchpad.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {

    fun getPreviousLaunches(): Flow<PagingData<Launch>>
    fun getUpcomingLaunches(): Flow<PagingData<Launch>>
}