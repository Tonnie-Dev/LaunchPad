package com.uxstate.launchpad.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Provider(val id: Int, val name: String, val type: String): Parcelable
