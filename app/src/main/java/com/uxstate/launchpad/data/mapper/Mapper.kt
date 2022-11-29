package com.uxstate.launchpad.data.mapper

import com.uxstate.launchpad.data.local.entities.PrevsEntity
import com.uxstate.launchpad.data.local.entities.UpsEntity
import com.uxstate.launchpad.data.remote.dtos.LaunchDTO
import com.uxstate.launchpad.domain.model.*

// LaunchDTO to entity
fun LaunchDTO.toPrevEntity(): PrevsEntity {
    return PrevsEntity(
            timeStamp = System.currentTimeMillis(),
            name = this.name.substringBefore('|'),
            imageUrl = this.image ?: "",
            status = Status(
                    name = this.statusDTO.name,
                    abbrev = this.statusDTO.abbrev,
                    description = this.statusDTO.description
            ),
            startWindowDate = this.windowStart,
            mission = Mission(
                    name = this.mission?.name ?: "Not Found",
                    description = this.mission?.description ?: "",
                    type = this.mission?.type ?: ""
            ),
            provider = Provider(
                    id = this.launchServiceProviderDTO.id,
                    name = this.launchServiceProviderDTO.name,
                    type = this.launchServiceProviderDTO.type ?: "Not Found"
            ),
            pad = Pad(
                    locationName = this.padDTO.location.name,
                    latitude = this.padDTO.latitude ?: "0.0",
                    longitude = this.padDTO.longitude ?: "0.0",
                    totalLaunchCount = this.padDTO.location.totalLaunchCount,
                    totalLandingCount = this.padDTO.location.totalLandingCount
            ),
            rocket = Rocket(
                    name = this.rocketDTO.configuration.name,
                    family = this.rocketDTO.configuration.family
            )
    )
}

// LaunchDTO to entity
fun LaunchDTO.toUpsEntity(): UpsEntity {

    return UpsEntity(
            timeStamp = System.currentTimeMillis(),
            name = this.name.substringBefore('|'),
            imageUrl = this.image ?: "",
            status = Status(
                    name = this.statusDTO.name,
                    abbrev = this.statusDTO.abbrev,
                    description = this.statusDTO.description
            ),
            startWindowDate = this.windowStart,
            mission = Mission(
                    name = this.mission?.name ?: "Not Found",
                    description = this.mission?.description ?: "",
                    type = this.mission?.type ?: ""
            ),
            provider = Provider(
                    id = this.launchServiceProviderDTO.id,
                    name = this.launchServiceProviderDTO.name,
                    type = this.launchServiceProviderDTO.type ?: "Not Found"
            ),
            pad = Pad(
                    locationName = this.padDTO.location.name,
                    latitude = this.padDTO.latitude ?: "0.0",
                    longitude = this.padDTO.longitude ?: "0.0"
            ),
            rocket = Rocket(
                    name = this.rocketDTO.configuration.name,
                    family = this.rocketDTO.configuration.family
            )
    )
}