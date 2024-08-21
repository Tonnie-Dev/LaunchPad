package com.uxstate.launchpad.data.remote.dto

import com.squareup.moshi.Json

data class OrbitDto(
    @Json(name = "abbrev")
    val abbrev: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
)
