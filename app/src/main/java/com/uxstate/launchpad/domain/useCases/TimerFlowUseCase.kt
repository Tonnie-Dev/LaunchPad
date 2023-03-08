package com.uxstate.launchpad.domain.useCases

import com.uxstate.launchpad.domain.model.Launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@Singleton
class TimerFlowUseCase @Inject constructor() {
    private var job: Job? = null
    operator fun invoke(launch: Launch, scope: CoroutineScope): Flow<Long> = flow {

        job?.cancel()

        job = scope.launch {
        }

        val countDownFrom = readStringDateToMillis(launch)
        var counter = countDownFrom
        emit(counter)

        while (counter > 0) {
            delay(1.seconds)

            counter--
            emit(counter)
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
