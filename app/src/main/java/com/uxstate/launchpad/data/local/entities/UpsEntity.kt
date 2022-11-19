package com.uxstate.launchpad.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uxstate.launchpad.domain.model.Mission
import com.uxstate.launchpad.domain.model.Pad
import com.uxstate.launchpad.domain.model.Provider
import com.uxstate.launchpad.domain.model.Rocket

@Entity(tableName = "ups_table")
data class UpsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val timeStamp: Long = 0L,
    val name: String,
    val imageUrl: String,
    val status: String,
    val startWindowDate: String,
    val mission: Mission,
    val provider: Provider,
    val pad: Pad,
    val rocket: Rocket
)
