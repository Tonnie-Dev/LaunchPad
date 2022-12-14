package com.uxstate.launchpad.data.remote.dtos

import com.squareup.moshi.Json

data class ApiResponseDTO(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val launchDTOS: List<LaunchDTO>
)
