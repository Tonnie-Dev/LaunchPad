package com.uxstate.launchpad.data.local.database.transaction

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.uxstate.launchpad.data.local.database.utils.TransactionProvider

abstract class RoomDatabaseTransactionProvider(private val database: RoomDatabase) :
    TransactionProvider {
    override suspend fun <R> withTransaction(doOnTransaction: suspend () -> R): R =
        database.withTransaction(doOnTransaction)
}