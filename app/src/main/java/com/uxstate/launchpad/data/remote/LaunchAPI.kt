package com.uxstate.launchpad.data.remote

import com.uxstate.launchpad.data.remote.dtos.ApiResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchAPI {


    @GET("launch/upcoming")
    suspend fun getUpcomingLaunches(
        @Query("limit") limit: Int = 10,
        @Query("offset") offSet: Int = 0
    ): ApiResponseDTO

    @GET("launch/previous")
    suspend fun getPreviousLaunches(
        @Query("limit") limit: Int = 10,
        offSet: Int = 0
    ): ApiResponseDTO

    companion object{

        const val BASE_URL = "https://lldev.thespacedevs.com/2.2.0/"
    }
}