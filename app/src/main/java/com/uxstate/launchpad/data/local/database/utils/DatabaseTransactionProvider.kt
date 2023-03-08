package com.uxstate.launchpad.data.local.database.utils

interface DatabaseTransactionProvider {
    suspend fun <R> withTransaction(doOnTransaction: suspend () -> R): R
}