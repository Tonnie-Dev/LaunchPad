package com.uxstate.launchpad.domain.model

data class ApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val launches: List<Launch>,
)
