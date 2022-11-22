package com.uxstate.launchpad.domain.use_cases

import android.os.Build
import androidx.annotation.RequiresApi
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

@RequiresApi(Build.VERSION_CODES.O)
class CountDownUseCase(private val timerScope: CoroutineScope) {
    operator fun invoke(launch: Launch): Flow<TimerState> = flow {

        val startWindowStringDate = launch.startWindowDate
        val startWindowLocalDate = convertStringToLocalDate(startWindowStringDate)

        //convert local date to seconds
        val zoneId = ZoneId.systemDefault()
        val totalSecondsToLaunch = startWindowLocalDate.atZone(ZoneId.systemDefault())
                .toEpochSecond()

        (totalSecondsToLaunch - 1 downTo 0).asFlow()
                .onEach { delay(1000) }
                .onStart { emit(totalSecondsToLaunch) }
                .conflate()
                .transform { remainingSeconds -> emit(TimerState(remainingSeconds)) }
    }

    private fun convertStringToLocalDate(date: String): LocalDateTime {

        val temporalAccessor: TemporalAccessor = DateTimeFormatter.ISO_INSTANT.parse(date)
        val instant: Instant = Instant.from(temporalAccessor)
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
    }
}
