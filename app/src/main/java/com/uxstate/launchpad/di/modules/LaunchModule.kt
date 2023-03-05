package com.uxstate.launchpad.di.modules

import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.data.remote.data_source.RemoteDataSourceImpl
import com.uxstate.launchpad.data.repository.LaunchRepositoryImpl
import com.uxstate.launchpad.domain.paging_source.RemoteDataSource
import com.uxstate.launchpad.domain.repository.LaunchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


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
        fun provideLaunchApi(retrofit: Retrofit): LaunchAPI =
            retrofit.create()
    }
}