package com.uxstate.launchpad.data.remote.dto

import com.squareup.moshi.Json

data class PadDto(
    @Json(name = "agency_id")
    val agencyId: Any?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "info_url")
    val infoUrl: Any?,
    @Json(name = "latitude")
    val latitude: String?,
    @Json(name = "location")
    val location: LocationDto,
    @Json(name = "longitude")
    val longitude: String?,
    @Json(name = "map_image")
    val mapImage: String,
    @Json(name = "map_url")
    val mapUrl: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "orbital_launch_attempt_count")
    val orbitalLaunchAttemptCount: Int,
    @Json(name = "total_launch_count")
    val totalLaunchCount: Int,
    @Json(name = "url")
    val url: String,
    @Json(name = "wiki_url")
    val wikiUrl: String?,
)
