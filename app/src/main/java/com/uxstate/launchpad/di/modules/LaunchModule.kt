package com.uxstate.launchpad.di.modules

import com.uxstate.launchpad.data.remote.LaunchApi
import com.uxstate.launchpad.data.remote.source.RemoteDataSourceImpl
import com.uxstate.launchpad.data.repository.LaunchRepositoryImpl
import com.uxstate.launchpad.domain.pagingSource.RemoteDataSource
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
        fun provideLaunchApi(retrofit: Retrofit): LaunchApi =
            retrofit.create()
    }
}