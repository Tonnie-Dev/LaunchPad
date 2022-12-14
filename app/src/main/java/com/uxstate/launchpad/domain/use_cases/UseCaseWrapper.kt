package com.uxstate.launchpad.domain.use_cases

data class UseCaseWrapper(
    val getPreviousLaunchesUseCase: GetPreviousLaunchesUseCase,
    val getUpcomingLaunchesUseCase: GetUpcomingLaunchesUseCase,
    val countDownUseCase: CountDownUseCase,
    val timerFlowUseCase: TimerFlowUseCase
)
