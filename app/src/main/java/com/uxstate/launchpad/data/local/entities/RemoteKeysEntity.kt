package com.uxstate.launchpad.data.local.entities

import androidx.room.PrimaryKey

data class RemoteKeysEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val prevPage: Int?,
    val nextPage: Int,
    val lastUpdated: Long?
)
