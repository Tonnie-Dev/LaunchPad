package com.uxstate.launchpad.domain.model

data class LaunchRemoteKeys(
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)