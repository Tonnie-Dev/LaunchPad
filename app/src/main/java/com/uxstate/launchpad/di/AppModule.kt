package com.uxstate.launchpad.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uxstate.launchpad.data.local.LaunchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.uxstate.launchpad.util.Constants
import com.uxstate.launchpad.util.Constants.CONNECT_TIMEOUT
import com.uxstate.launchpad.util.Constants.READ_TIMEOUT

@Module
@InstallIn(SingletonComponent::class)


object AppModule {

    @Provides
    @Singleton

    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {

        return Room.databaseBuilder(context, LaunchDatabase::class.java, Constants1.DATABASE_NAME)
                .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {

            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    @Singleton

    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }


}