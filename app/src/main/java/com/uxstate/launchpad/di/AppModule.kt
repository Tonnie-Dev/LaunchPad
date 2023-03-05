package com.uxstate.launchpad.di

import android.content.Context
import androidx.room.Room
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.domain.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LaunchDatabase =
        Room.databaseBuilder(
            context,
            LaunchDatabase::class.java,
            LaunchDatabase.DATABASE_NAME
        ).build()
}
