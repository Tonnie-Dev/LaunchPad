package com.uxstate.launchpad.domain.use_cases

import android.os.Build
import androidx.annotation.RequiresApi
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimerState
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@RequiresApi(Build.VERSION_CODES.O)
class CountDownUseCase() {
    operator fun invoke(launch: Launch): Flow<TimerState> = flow {

        val startWindowStringDate = launch.startWindowDate
        val startWindowLocalDate = convertStringToLocalDate(startWindowStringDate)

        // convert local date to seconds
        val zoneId = ZoneId.systemDefault()
        val totalSecondsToLaunch = startWindowLocalDate.atZone(ZoneId.systemDefault())
            .toEpochSecond()

        if (totalSecondsToLaunch > 0) {

            (totalSecondsToLaunch - 1 downTo 0).asFlow()
                .onEach { delay(1000) } // Each second later emit a number
                .onStart { emit(totalSecondsToLaunch) } // Emit total seconds immediately
                .conflate() // In case the operation onTick takes
                // some time, conflate keeps the time ticking separately
                .transform { remainingSeconds ->
                    emit(TimerState(remainingSeconds))
                }

            /* (totalSecondsToLaunch..0).asSequence()
                 .asFlow()
                 .onEach {
                     Timber.i("Delay Called")
                     delay(1000)
                     emit(TimerState(it))
                 }*/
        }

        // .onStart { emit(TimerState()) }
        // .conflate()
        // .transform { remainingSeconds -> emit(TimerState(remainingSeconds)) }
    }

    private fun convertStringToLocalDate(date: String): LocalDateTime {

        val temporalAccessor: TemporalAccessor = DateTimeFormatter.ISO_INSTANT.parse(date)
        val instant: Instant = Instant.from(temporalAccessor)
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
    }
}
