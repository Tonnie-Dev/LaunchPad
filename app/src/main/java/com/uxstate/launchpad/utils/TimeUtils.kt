package com.uxstate.launchpad.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import com.uxstate.launchpad.domain.model.Launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.formatLaunchDatabaseStringDate(): String {

    // convert string date to local date
    val temporalAccessor: TemporalAccessor = DateTimeFormatter.ISO_INSTANT.parse(this)
    val instant: Instant = Instant.from(temporalAccessor)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())
    
    val pattern = "MMM dd, yyyy hh:mm a"
    val dateFormatter = DateTimeFormatter.ofPattern(pattern)

    return localDateTime.format(dateFormatter)
}

fun Launch.convertLaunchDatabaseDateToSeconds(): Long {

    // convert string date to local date
    val temporalAccessor: TemporalAccessor =
        DateTimeFormatter.ISO_INSTANT.parse(this.startWindowDate)
    val instant: Instant = Instant.from(temporalAccessor)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())

    // convert local date to millis

    return localDateTime.atZone(ZoneId.systemDefault())
            .toEpochSecond()
}

fun Launch.generateSecondsFlow(): Flow<Long> = flow {
    val countDownFrom = convertLaunchDatabaseDateToSeconds()
    var counter = countDownFrom
    emit(counter)

    while (counter > 0) {
        delay(1.seconds)

        counter--
        emit(counter)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun addAnimation(duration: Int = 800): ContentTransform {

    return (slideInVertically(
            animationSpec = tween(durationMillis = duration)
    ) { height -> height } + fadeIn(
            animationSpec = tween(durationMillis = duration)
    )).togetherWith(slideOutVertically(
            animationSpec =
            tween(durationMillis = duration)
    ) { height -> -height } + fadeOut(tween(durationMillis = duration)))
}

fun Date.getCurrentDateTime(): String {
    // Database Pattern 2024-08-12T10:37:00Z
    val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}
