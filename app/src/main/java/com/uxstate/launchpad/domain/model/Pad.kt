package com.uxstate.launchpad.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pad(val locationName: String, val latitude: String, val longitude: String): Parcelable
