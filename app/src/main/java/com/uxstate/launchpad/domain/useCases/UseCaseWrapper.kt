package com.uxstate.launchpad.domain.useCases

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class UseCaseWrapper @Inject constructor(
    val getPreviousLaunchesUseCase: GetPreviousLaunchesUseCase,
    val getUpcomingLaunchesUseCase: GetUpcomingLaunchesUseCase,
    val countDownUseCase: CountDownUseCase,
    val timerFlowUseCase: TimerFlowUseCase
)
