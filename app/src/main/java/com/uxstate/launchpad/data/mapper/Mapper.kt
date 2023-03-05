package com.uxstate.launchpad.data.mapper

import com.uxstate.launchpad.data.local.entities.PrevsEntity
import com.uxstate.launchpad.data.local.entities.UpsEntity
import com.uxstate.launchpad.data.remote.dto.*
import com.uxstate.launchpad.domain.model.*


fun LaunchDto.toPrevEntity(): PrevsEntity =
    PrevsEntity(
        timeStamp = System.currentTimeMillis(),
        name = name.substringBefore('|'),
        imageUrl = image ?: "",
        status = statusDto.toStatus(),
        startWindowDate = windowStart,
        mission = missionDto.toMission(),
        provider = launchServiceProviderDto.toProvider(),
        pad = padDto.toPad(),
        rocket = rocketDto.toRocket(),
    )


fun LaunchDto.toUpsEntity(): UpsEntity =
    UpsEntity(
        timeStamp = System.currentTimeMillis(),
        name = name.substringBefore('|'),
        imageUrl = image ?: "",
        status = statusDto.toStatus(),
        startWindowDate = windowStart,
        mission = missionDto.toMission(),
        provider = launchServiceProviderDto.toProvider(),
        pad = padDto.toPad(),
        rocket = rocketDto.toRocket(),
    )

fun StatusDto.toStatus(): Status =
    Status(
        name = name,
        abbrev = abbrev,
        description = description
    )

fun LaunchServiceProviderDto.toProvider(): Provider =
    Provider(
        id = id,
        name = name,
        type = type ?: "Not Found"
    )

fun PadDto.toPad() : Pad =
    Pad(
        locationName = location.name,
        latitude = latitude ?: "0.0",
        longitude = longitude ?: "0.0",
        complex = name,
        totalLaunchCount = location.totalLaunchCount,
        totalLandingCount = location.totalLandingCount
    )

fun RocketDto.toRocket() : Rocket =
    Rocket(
        name = configuration.name,
        family = configuration.family
    )

fun MissionDto?.toMission(): Mission = Mission(
    name = this?.name ?: "Not Found",
    description = this?.description ?: "",
    type = this?.type ?: ""
)

