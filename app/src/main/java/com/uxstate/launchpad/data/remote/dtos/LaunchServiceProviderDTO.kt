package com.uxstate.launchpad.data.remote.dtos

import com.squareup.moshi.Json

data class LaunchServiceProviderDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)