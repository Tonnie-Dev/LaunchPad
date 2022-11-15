package com.uxstate.launchpad.data.mapper

import com.uxstate.launchpad.data.local.entities.LaunchEntity
import com.uxstate.launchpad.data.remote.dtos.LaunchDTO
import com.uxstate.launchpad.domain.model.Mission
import com.uxstate.launchpad.domain.model.Pad
import com.uxstate.launchpad.domain.model.Provider
import com.uxstate.launchpad.domain.model.Rocket

// LaunchDTO to entity

fun LaunchDTO.toEntity(): LaunchEntity {

    return LaunchEntity(
        id = this.id,
        name = this.name,
        imageUrl = this.image,
        status = this.statusDTO.abbrev,
        startWindowDate = this.windowStart,
        mission = Mission(
            name = this.mission?.name ?: "Not Found",
            description = this.mission?.description ?: "",
            type = this.mission?.type ?: ""
        ),
        provider = Provider(
            id = this.launchServiceProviderDTO.id,
            name = this.launchServiceProviderDTO.name,
            type = this.launchServiceProviderDTO.type
        ),
        pad = Pad(
            locationName = this.padDTO.location.name,
            latitude = this.padDTO.latitude,
            longitude = this.padDTO.longitude
        ),
        rocket = Rocket(
            name = this.rocketDTO.configuration.name,
            family = this.rocketDTO.configuration.family
        )
    )
}