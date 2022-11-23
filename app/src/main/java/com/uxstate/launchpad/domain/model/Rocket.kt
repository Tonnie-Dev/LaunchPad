package com.uxstate.launchpad.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rocket(val name: String, val family: String) : Parcelable
