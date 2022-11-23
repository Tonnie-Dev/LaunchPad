package com.uxstate.launchpad.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Launch(
    val id: Int,
    val name: String,
    val mission: Mission,
    val imageUrl: String,
    val provider: Provider,
    val status: String,
    val pad: Pad,
    val startWindowDate: String,
    val rocket: Rocket
) : Parcelable
