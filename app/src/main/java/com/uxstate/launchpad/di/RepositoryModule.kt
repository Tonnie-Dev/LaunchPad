package com.uxstate.launchpad.di

import com.uxstate.launchpad.data.repository.LaunchRepositoryImpl
import com.uxstate.launchpad.domain.repository.LaunchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
   abstract fun provideLaunchRepository (repositoryImpl: LaunchRepositoryImpl):LaunchRepository
   
}