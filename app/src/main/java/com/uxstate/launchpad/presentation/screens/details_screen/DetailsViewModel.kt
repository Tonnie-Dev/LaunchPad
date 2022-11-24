package com.uxstate.launchpad.presentation.screens.details_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.use_cases.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DetailsViewModel @Inject constructor(

    private val wrapper: UseCaseWrapper
) : ViewModel() {

    private var _timerStateFlow = MutableStateFlow(0)
    val timerStateFlow = _timerStateFlow.asStateFlow()

    private var timerJob: Job? = null
    // Create the timer flow
    val timer = (0..Int.MAX_VALUE)
        .asSequence()
        .asFlow()
        .onEach { delay(1_000) }

    fun collectTimerFlow(launch: Launch) {

        timerJob?.cancel()
        // reinitialize job

        timerJob = viewModelScope.launch {

            wrapper.countDownUseCase(launch).collectLatest {
                // _timerStateFlow.value = it
            }
        }
    }

    fun runTimer(launch: Launch) = flow {

        viewModelScope.launch {

            timer.collectLatest {

                _timerStateFlow.value = it
            }
        }
        val countDownFrom = readStringDateToMillis(launch)
        var counter = countDownFrom
        emit(countDownFrom)

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

    private fun alternateFlow(launch: Launch) {
        val countDownFrom = readStringDateToMillis(launch)
        /*viewModelScope.launch {

            (countDownFrom..0).asFlow() // Emit total - 1 because the first was emitted onStart
                .onEach { delay(1000) } // Each second later emit a number
                .onStart { emit(countDownFrom) } // Emit total seconds immediately
                .conflate() // In case the operation onTick
                // takes some time, conflate keeps the time ticking separately
                .transform { remainingSeconds ->
                    emit(TimerState(remainingSeconds))
                }.onCompletion { _timerStateFlow.emit(TimerState()) }
                .collect {
                    _timerStateFlow.emit(it)
                }
        }*/
    }
}