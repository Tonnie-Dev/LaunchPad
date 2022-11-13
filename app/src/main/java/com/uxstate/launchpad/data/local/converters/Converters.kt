package com.uxstate.launchpad.data.local.converters

import androidx.room.TypeConverter
import com.uxstate.launchpad.domain.model.Mission

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

}

