package com.uxstate.launchpad.data.local.database.transaction

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.uxstate.launchpad.data.local.database.utils.DatabaseTransactionProvider

abstract class RoomDatabaseTransactionProvider(private val database: RoomDatabase) :
    DatabaseTransactionProvider {
    override suspend fun <R> withTransaction(doOnTransaction: suspend () -> R): R =
        database.withTransaction(doOnTransaction)
}