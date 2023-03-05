package com.uxstate.launchpad.data.repository

import androidx.paging.PagingData
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.pagingSource.RemoteDataSource
import com.uxstate.launchpad.domain.repository.LaunchRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class LaunchRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
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
