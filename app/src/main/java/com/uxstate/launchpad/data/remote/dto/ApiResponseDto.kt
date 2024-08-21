package com.uxstate.launchpad.data.remote.dto

import com.squareup.moshi.Json

data class ApiResponseDto(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val launchDtos: List<LaunchDto>,
)
