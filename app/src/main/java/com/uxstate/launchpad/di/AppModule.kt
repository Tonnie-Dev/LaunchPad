package com.uxstate.launchpad.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)


object AppModule {

    @Provides
    @Singleton

    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {

        return Room.databaseBuilder(context, LaunchDatabase::class.java, Constants.DATABASE_NAME)
                .build()
    }
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {

            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    @Singleton

    fun provideOkHttpClient ():OkHttpClient {

        return OkHttpClient().apply { cont }
    }


}