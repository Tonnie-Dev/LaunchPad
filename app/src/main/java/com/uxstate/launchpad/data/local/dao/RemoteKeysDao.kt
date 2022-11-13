package com.uxstate.launchpad.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uxstate.launchpad.data.local.entities.RemoteKeysEntity



interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(keys: List<RemoteKeysEntity>)

    @Query("SELECT * FROM remote_keys_table WHERE id=:id")
    suspend fun getRemoteKeyById(id: Int): RemoteKeysEntity

    @Query("DELETE FROM remote_keys_table")
    suspend fun clearRemoteKeys()
}