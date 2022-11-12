package com.uxstate.launchpad.data.remote.dtos


import com.squareup.moshi.Json

data class Rocket(
    @Json(name = "configuration")
    val configuration: Configuration,
    @Json(name = "id")
    val id: Int
)