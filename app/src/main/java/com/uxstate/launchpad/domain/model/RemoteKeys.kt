package com.uxstate.launchpad.domain.model

data class RemoteKeys(
    val id: Int,
    val prevPage: String?,
    val nextPage: String?,
    val lastUpdated: Long?,
)
