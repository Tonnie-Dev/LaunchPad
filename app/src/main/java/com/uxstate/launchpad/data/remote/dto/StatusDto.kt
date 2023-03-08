package com.uxstate.launchpad.data.remote.dto

import com.squareup.moshi.Json

data class StatusDto(
    @Json(name = "abbrev")
    val abbrev: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)
