package com.uxstate.launchpad.data.remote

import com.uxstate.launchpad.data.remote.dto.ApiResponseDto
import com.uxstate.launchpad.utils.Constants.DEFAULT_API_ITEMS_LIMIT
import com.uxstate.launchpad.utils.Constants.OFFSET_STARTING_INDEX
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchApi {

    @GET("launch/upcoming")
    suspend fun getUpcomingLaunches(
        @Query("limit") limit: Int = DEFAULT_API_ITEMS_LIMIT,
        @Query("offset") offSet: Int = OFFSET_STARTING_INDEX
    ): ApiResponseDto

    @GET("launch/previous")
    suspend fun getPreviousLaunches(
        @Query("limit")
        limit: Int = DEFAULT_API_ITEMS_LIMIT,
        @Query("offset")
        offSet: Int = OFFSET_STARTING_INDEX
    ): ApiResponseDto

    companion object {

        const val BASE_URL = "https://lldev.thespacedevs.com/2.2.0/"
    }
}
