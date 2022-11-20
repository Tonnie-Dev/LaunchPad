package com.uxstate.launchpad.domain.model

import java.time.LocalDateTime

data class Launch(
    val id: Int,
    val name: String,
    val mission: Mission,
    val imageUrl: String,
    val provider: Provider,
    val status: String,
    val pad: Pad,
    val startWindowDate: LocalDateTime,
    val rocket: Rocket
)
