package com.uxstate.launchpad.di.modules

import com.uxstate.launchpad.data.local.dao.LaunchDao
import com.uxstate.launchpad.data.local.database.LaunchDatabase
import com.uxstate.launchpad.data.remote.api.LaunchApi
import com.uxstate.launchpad.data.remote.source.RemoteDataSourceImpl
import com.uxstate.launchpad.data.repository.LaunchRepositoryImpl
import com.uxstate.launchpad.domain.pagingSource.RemoteDataSource
import com.uxstate.launchpad.domain.repository.LaunchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
interface LaunchModule {

    @Binds
    @Singleton
    fun bindLaunchRepository(repository: LaunchRepositoryImpl): LaunchRepository

    @Binds
    @Singleton
    fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun provideLaunchApi(retrofit: Retrofit): LaunchApi =
            retrofit.create()

        @Provides
        @Singleton
        fun provideLaunchDao(database: LaunchDatabase): LaunchDao =
            database.launchDao
    }
}