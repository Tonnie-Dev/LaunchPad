package com.uxstate.launchpad.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uxstate.launchpad.data.local.entities.PrevsEntity
import com.uxstate.launchpad.data.local.entities.UpsEntity
import com.uxstate.launchpad.domain.model.Launch

@Dao
interface LaunchDao {

    // PREVIOUS LAUNCHES
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreviousLaunches(launches: List<PrevsEntity>)

    @Query("SELECT * FROM prevs_table")
    fun getPreviousLaunches(): PagingSource<Int, Launch>

    @Query("DELETE FROM prevs_table")
    suspend fun clearPreviousLaunches()

    // UPS_LAUNCHES

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingLaunches(launches: List<UpsEntity>)

    @Query("SELECT * FROM ups_table")
    fun getUpcomingLaunches(): PagingSource<Int, Launch>

    @Query("DELETE FROM ups_table")
    suspend fun clearUpcomingLaunches()

    @Query("SELECT * FROM ups_table WHERE id = (SELECT MAX(id) FROM ups_table) ")
    suspend fun selectLast(): UpsEntity?
}