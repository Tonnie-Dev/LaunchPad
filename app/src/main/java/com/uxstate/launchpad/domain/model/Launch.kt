package com.uxstate.launchpad.domain.model

data class Launch(
    val id:String,
    val name: String,
    val mission: Mission,
    val imageUrl: String,
    val provider: Provider,
    val status: String,
    val pad: Pad,
    val startWindowDate: String,
    val rocket: Rocket
)
