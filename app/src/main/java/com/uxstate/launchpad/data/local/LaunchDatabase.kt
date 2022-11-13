package com.uxstate.launchpad.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uxstate.launchpad.data.local.converters.Converters
import com.uxstate.launchpad.data.local.dao.LaunchDao
import com.uxstate.launchpad.data.local.dao.RemoteKeysDao
import com.uxstate.launchpad.data.local.entities.LaunchEntity
import com.uxstate.launchpad.data.local.entities.RemoteKeysEntity

@Database(
        entities = [LaunchEntity::class, RemoteKeysEntity::class],
        version = 1,
        exportSchema = true
)
@TypeConverters(Converters::class)
abstract class LaunchDatabase : RoomDatabase() {
    abstract val launchDao: LaunchDao
    abstract val remoteKeysDao: RemoteKeysDao
}