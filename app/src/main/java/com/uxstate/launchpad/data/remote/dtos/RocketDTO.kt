package com.uxstate.launchpad.data.remote.dtos

import com.squareup.moshi.Json

data class RocketDTO(
    @Json(name = "configuration")
    val configuration: ConfigurationDTO,
    @Json(name = "id")
    val id: Int
)