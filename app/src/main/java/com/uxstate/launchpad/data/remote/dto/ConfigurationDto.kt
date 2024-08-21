package com.uxstate.launchpad.data.remote.dto

import com.squareup.moshi.Json

data class ConfigurationDto(
    @Json(name = "family")
    val family: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "variant")
    val variant: String,
)
