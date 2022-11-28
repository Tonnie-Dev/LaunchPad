package com.uxstate.launchpad.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Status(val name:String, val abbrev:String, val description:String) :Parcelable{
}