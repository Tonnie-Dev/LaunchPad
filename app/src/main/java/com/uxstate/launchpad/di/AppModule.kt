package com.uxstate.launchpad.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.uxstate.launchpad.data.local.LaunchDatabase
import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.domain.repository.LaunchRepository
import com.uxstate.launchpad.domain.use_cases.GetPreviousLaunchesUseCase
import com.uxstate.launchpad.domain.use_cases.GetUpcomingLaunchesUseCase
import com.uxstate.launchpad.domain.use_cases.UseCaseWrapper
import com.uxstate.launchpad.util.Constants.CONNECT_TIMEOUT
import com.uxstate.launchpad.util.Constants.DATABASE_NAME
import com.uxstate.launchpad.util.Constants.READ_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton

    fun provideDatabase(@ApplicationContext context: Context): LaunchDatabase {

        return Room.databaseBuilder(context, LaunchDatabase::class.java, DATABASE_NAME)
                .build()
    }

    /*For debugging purposes it’s nice to have a log feature integrated to
 show request and response information. */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {

            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /* connect timeout defines a time period in which our
       client should establish a connection with a target host.
  By default, for the OkHttpClient, this timeout is set to 10 seconds.   */

    /*maximum time of inactivity between two data packets when waiting for the
    server's response.The default timeout of 10 seconds */
    @Provides
    @Singleton

    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideLaunchAPI(): LaunchAPI {

        val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        return Retrofit.Builder()
                .baseUrl(LaunchAPI.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create()
    }

    @Provides
    @Singleton
    fun provideUseCaseWrapper(repository: LaunchRepository): UseCaseWrapper {

        return UseCaseWrapper(
                getPreviousLaunchesUseCase = GetPreviousLaunchesUseCase(repository = repository),
                getUpcomingLaunchesUseCase = GetUpcomingLaunchesUseCase(repository = repository)
        )
    }
}