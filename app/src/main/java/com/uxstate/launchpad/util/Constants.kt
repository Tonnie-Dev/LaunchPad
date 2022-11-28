package com.uxstate.launchpad.util

import com.uxstate.launchpad.domain.model.Launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import kotlin.time.Duration.Companion.seconds

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
