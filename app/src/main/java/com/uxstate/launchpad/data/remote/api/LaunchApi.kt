package com.uxstate.launchpad.data.remote.api

import com.uxstate.launchpad.data.remote.api.constants.LaunchApiEndpoints
import com.uxstate.launchpad.data.remote.api.constants.LaunchApiParams
import com.uxstate.launchpad.data.remote.dto.ApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchApi {

    companion object {
        const val BASE_URL = "https://lldev.thespacedevs.com/2.2.0/"
    }

    @GET(LaunchApiEndpoints.LAUNCHES_UPCOMING)
    suspend fun getUpcomingLaunches(
        @Query(LaunchApiParams.QUERY_LIMIT)
        limit: Int = LaunchApiParams.DEFAULT_LIMIT,
        @Query(LaunchApiParams.QUERY_OFFSET)
        offset: Int = LaunchApiParams.DEFAULT_OFFSET
    ): ApiResponseDto

    @GET(LaunchApiEndpoints.LAUNCHES_PREVIOUS)
    suspend fun getPreviousLaunches(
        @Query(LaunchApiParams.QUERY_LIMIT)
        limit: Int = LaunchApiParams.DEFAULT_LIMIT,
        @Query(LaunchApiParams.QUERY_OFFSET)
        offset: Int = LaunchApiParams.DEFAULT_OFFSET
    ): ApiResponseDto
}
