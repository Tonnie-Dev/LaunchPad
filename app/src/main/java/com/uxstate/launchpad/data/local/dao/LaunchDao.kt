package com.uxstate.launchpad.data.local.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uxstate.launchpad.data.local.entities.LaunchEntity
import com.uxstate.launchpad.domain.model.Launch

@Dao
interface LaunchDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launches: List<LaunchEntity>)

    @Query("SELECT * FROM launch_table")
   fun getLaunches():PagingSource<Int,Launch>

    @Query("DELETE FROM launch_table")
    suspend fun clearLaunches()
}