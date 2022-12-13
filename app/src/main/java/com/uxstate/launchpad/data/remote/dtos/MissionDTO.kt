package com.uxstate.launchpad.data.remote.dtos

import com.squareup.moshi.Json

data class MissionDTO(
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "launch_designator")
    val launchDesignator: Any?,
    @Json(name = "name")
    val name: String,
    @Json(name = "orbit")
    val orbit: OrbitDTO?,
    @Json(name = "type")
    val type: String
)
