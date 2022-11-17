package com.uxstate.launchpad.data.repository

import androidx.paging.PagingData
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.paging_source.RemoteDataSource
import com.uxstate.launchpad.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class LaunchRepositoryImpl @Inject constructor(
    private val dataSource:
    RemoteDataSource
) : LaunchRepository {

    override fun getPreviousLaunches(): Flow<PagingData<Launch>> {
        Timber.i("Repo getPreviousLaunches Called")
        return dataSource.getPreviousLaunches()
    }

    override fun getUpcomingLaunches(): Flow<PagingData<Launch>> {

        Timber.i("Repo getUpcomingLaunches Called")
        return dataSource.getUpcomingLaunches()
    }
}