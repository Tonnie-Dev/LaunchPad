package com.uxstate.launchpad.data.remote.dtos

import com.squareup.moshi.Json

data class Orbit(
    @Json(name = "abbrev")
    val abbrev: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)