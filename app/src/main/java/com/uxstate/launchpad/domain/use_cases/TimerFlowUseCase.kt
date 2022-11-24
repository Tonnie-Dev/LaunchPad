package com.uxstate.launchpad.domain.use_cases

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
class TimerFlowUseCase() {


    operator fun invoke(launch: Launch): Flow<Long> = flow {

        val countDownFrom = readStringDateToMillis(launch)
        var counter = countDownFrom
        emit(counter)

        while (counter > 0) {
            delay(1.seconds)
            emit(counter)
            counter--
        }
    }


    private fun readStringDateToMillis(launch: Launch): Long {

        // convert string date to local date
        val temporalAccessor: TemporalAccessor =
            DateTimeFormatter.ISO_INSTANT.parse(launch.startWindowDate)
        val instant: Instant = Instant.from(temporalAccessor)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())

        // convert local date to millis

        return localDateTime.atZone(ZoneId.systemDefault())
                .toEpochSecond()
    }
}