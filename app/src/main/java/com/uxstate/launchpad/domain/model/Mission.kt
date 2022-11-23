package com.uxstate.launchpad.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mission(
    val name: String,
    val description: String,
    val type: String
) : Parcelable
