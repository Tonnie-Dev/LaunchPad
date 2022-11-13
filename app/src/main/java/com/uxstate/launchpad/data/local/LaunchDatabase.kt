package com.uxstate.launchpad.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uxstate.launchpad.data.local.converters.Converters
import com.uxstate.launchpad.data.local.dao.LaunchDao
import com.uxstate.launchpad.data.local.entities.LaunchEntity

@Database(entities = [LaunchEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class LaunchDatabase : RoomDatabase() {
    abstract val launchDao: LaunchDao
}