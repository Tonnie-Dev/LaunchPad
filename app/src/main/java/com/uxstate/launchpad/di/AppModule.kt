package com.uxstate.launchpad.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.util.Constants
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)


object AppModule {

    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {

        return Room.databaseBuilder(context, LaunchDatabase::class.java, Constants.DATABASE_NAME)
                .build()
    }

}