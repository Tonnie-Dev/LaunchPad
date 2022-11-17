package com.uxstate.launchpad.data.remote

import com.uxstate.launchpad.data.remote.dtos.ApiResponseDTO
import com.uxstate.launchpad.util.Constants.DEFAULT_API_ITEMS_LIMIT
import com.uxstate.launchpad.util.Constants.OFFSET_STARTING_INDEX
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchAPI {

    @GET("launch/upcoming")
    suspend fun getUpcomingLaunches(
        @Query("limit") limit: Int = DEFAULT_API_ITEMS_LIMIT,
        @Query("offset") offSet: Int = DEFAULT_API_ITEMS_LIMIT
    ): ApiResponseDTO

    @GET("launch/previous")
    suspend fun getPreviousLaunches(
        @Query("limit")
        limit: Int = DEFAULT_API_ITEMS_LIMIT,
        @Query("offset")
        offSet: Int = OFFSET_STARTING_INDEX
    ): ApiResponseDTO

    companion object {

        const val BASE_URL = "https://lldev.thespacedevs.com/2.2.0/"
    }
}