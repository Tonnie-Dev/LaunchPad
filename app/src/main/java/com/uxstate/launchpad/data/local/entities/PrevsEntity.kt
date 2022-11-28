package com.uxstate.launchpad.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uxstate.launchpad.domain.model.*

@Entity(tableName = "prevs_table")
data class PrevsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val timeStamp: Long = 0L,
    val name: String,
    val imageUrl: String,
    val status: Status,
    val startWindowDate: String,
    val mission: Mission,
    val provider: Provider,
    val pad: Pad,
    val rocket: Rocket
)
