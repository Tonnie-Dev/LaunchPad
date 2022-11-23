package com.uxstate.launchpad.presentation.screens.details_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimerState
import com.uxstate.launchpad.domain.use_cases.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*



@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DetailsViewModel @Inject constructor(

    private val wrapper: UseCaseWrapper
) : ViewModel() {

    private var _timerStateFlow = MutableStateFlow(TimerState())
    val timerStateFlow = _timerStateFlow.asStateFlow()

    private var timerJob: Job? = null

    fun collectTimerFlow(launch: Launch) {

        timerJob?.cancel()
        // reinitialize job

        timerJob = viewModelScope.launch {

            wrapper.countDownUseCase(launch)
                .collectLatest {
                    _timerStateFlow.value = it
                }
        }
    }

    fun runTimer(launch: Launch) = flow {
        val countDownFrom = readStringDateToMillis(launch)
        var counter = countDownFrom
        emit(countDownFrom)

        while (counter > 0) {
            delay(1000)
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