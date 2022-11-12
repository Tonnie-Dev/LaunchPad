package com.uxstate.launchpad.data.remote.dtos


import com.squareup.moshi.Json

data class Location(
    @Json(name = "country_code")
    val countryCode: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "map_image")
    val mapImage: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "total_landing_count")
    val totalLandingCount: Int,
    @Json(name = "total_launch_count")
    val totalLaunchCount: Int,
    @Json(name = "url")
    val url: String
)