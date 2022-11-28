package com.uxstate.launchpad.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

object Constants {
    const val OFFSET_STARTING_INDEX = 0
    const val PAGE_SIZE = 3
    const val DEFAULT_API_ITEMS_LIMIT = 10
    const val INITIAL_LOAD_SIZE = 10
    const val DATABASE_NAME = "LaunchDatabase"
    const val READ_TIMEOUT = 15L
    const val CONNECT_TIMEOUT = 15L
    const val CACHE_TIMEOUT = 720
}

fun formatStringDate(date: String): String {

    // convert string date to local date
    val temporalAccessor: TemporalAccessor = DateTimeFormatter.ISO_INSTANT.parse(date)
    val instant: Instant = Instant.from(temporalAccessor)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())

    val pattern = "MMM dd, yyyy hh:mm a"
    val dateFormatter = DateTimeFormatter.ofPattern(pattern)

    return localDateTime.format(dateFormatter)
}