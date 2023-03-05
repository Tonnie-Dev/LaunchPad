package com.uxstate.launchpad.data.local.database.utils

interface TransactionProvider {
    suspend fun <R> withTransaction(doOnTransaction: suspend () -> R): R
}