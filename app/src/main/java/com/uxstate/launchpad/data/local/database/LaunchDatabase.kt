package com.uxstate.launchpad.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uxstate.launchpad.data.local.converters.Converters
import com.uxstate.launchpad.data.local.dao.LaunchDao
import com.uxstate.launchpad.data.local.entities.PrevsEntity
import com.uxstate.launchpad.data.local.entities.UpsEntity

@Database(
    entities = [PrevsEntity::class, UpsEntity::class],
    version = LaunchDatabase.DATABASE_VERSION,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class LaunchDatabase : RoomDatabase() {
    abstract val launchDao: LaunchDao

    companion object {
        const val DATABASE_NAME = "LaunchDatabase"
        const val DATABASE_VERSION = 1
    }
}
