package com.uxstate.launchpad.presentation.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimerState
import com.uxstate.launchpad.domain.use_cases.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel @Inject constructor(
    private val useCaseWrapper: UseCaseWrapper
) : ViewModel() {

    private var _timerStateFlow = MutableStateFlow(TimerState())
    val timerStateFlow = _timerStateFlow.asStateFlow()

    private var timerJob: Job? = null
    val previousLaunches = useCaseWrapper.getPreviousLaunchesUseCase()
    val upcomingLaunches = useCaseWrapper.getUpcomingLaunchesUseCase()

    fun collectTimerFlow(launch: Launch) = useCaseWrapper.timerFlowUseCase(launch, viewModelScope)
}