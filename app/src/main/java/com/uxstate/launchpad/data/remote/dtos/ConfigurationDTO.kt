package com.uxstate.launchpad.data.remote.dtos

import com.squareup.moshi.Json

data class ConfigurationDTO(
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
    val variant: String
)