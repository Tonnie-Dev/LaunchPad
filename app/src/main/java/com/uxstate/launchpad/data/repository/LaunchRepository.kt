package com.uxstate.launchpad.data.repository

import com.uxstate.launchpad.domain.paging_source.RemoteDataSource
import com.uxstate.launchpad.domain.repository.LaunchRepository
import javax.inject.Inject

class LaunchRepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource):LaunchRepository {
}