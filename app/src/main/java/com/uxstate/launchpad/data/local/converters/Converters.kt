package com.uxstate.launchpad.data.local.converters

import androidx.room.TypeConverter
import com.uxstate.launchpad.domain.model.Mission
import com.uxstate.launchpad.domain.model.Provider

class Converters {
    @TypeConverter
    fun writeMissionToRoom(mission: Mission): String {

        val missionName = mission.name
        val missionDesc = mission.description
        val missionType = mission.type
        val missionListString = listOf(missionName, missionDesc, missionType)

        return missionListString.joinToString(separator = ",")
    }

    @TypeConverter
    fun readMissionFromRoom(roomString: String): Mission {


        val roomStringList = roomString.split(",")
                .map { it }

        return Mission(
                name = roomStringList[0],
                description = roomStringList[1],
                type = roomStringList[2]
        )
    }

    @TypeConverter
    fun writeProviderToRoom(provider: Provider): String {
        val providerId = provider.id.toString()
        val providerName = provider.name
        val providerType = provider.type

        val providerStringList = listOf(providerId, providerName, providerType)
        return providerStringList.joinToString(separator = ",")

    }

}

