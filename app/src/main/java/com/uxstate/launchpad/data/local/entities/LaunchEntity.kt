package com.uxstate.launchpad.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uxstate.launchpad.domain.model.Mission
import com.uxstate.launchpad.domain.model.Pad
import com.uxstate.launchpad.domain.model.Provider
import com.uxstate.launchpad.domain.model.Rocket

@Entity(tableName = "launch_table")
data class LaunchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val imageUrl: String,
    val status: String,
    val startWindowDate: String,
    val mission: Mission,
    val provider: Provider,
    val pad: Pad,
    val rocket: Rocket
)
