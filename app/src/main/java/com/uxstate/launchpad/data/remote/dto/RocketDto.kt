package com.uxstate.launchpad.data.remote.dto

import com.squareup.moshi.Json

data class RocketDto(
    @Json(name = "configuration")
    val configuration: ConfigurationDto,
    @Json(name = "id")
    val id: Int
)
