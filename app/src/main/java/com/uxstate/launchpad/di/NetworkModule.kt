package com.uxstate.launchpad.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.uxstate.launchpad.data.remote.LaunchAPI
import com.uxstate.launchpad.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    /*
    For debugging purposes it’s nice to have a log feature integrated to
    show request and response information.
    */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /*
    Connect timeout defines a time period in which our
    client should establish a connection with a target host.
    By default, for the OkHttpClient, this timeout is set to 10 seconds.
    */

    /*
    Maximum time of inactivity between two data packets when waiting for the
    server's response.The default timeout of 10 seconds
    */
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideMoshiConverter(): Converter.Factory {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideRetrofit(converter: Converter.Factory) =
        Retrofit.Builder()
            .baseUrl(LaunchAPI.BASE_URL)
            .addConverterFactory(converter)
            .build()
}