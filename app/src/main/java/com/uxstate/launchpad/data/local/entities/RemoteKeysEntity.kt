package com.uxstate.launchpad.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys_table")
data class RemoteKeysEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val prevPage: Int?,
    val nextPage: Int,
    val lastUpdated: Long?
)
