package com.uxstate.launchpad.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.uxstate.launchpad.domain.useCases.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        useCaseWrapper: UseCaseWrapper,
    ) : ViewModel() {
        val previousLaunches = useCaseWrapper.getPreviousLaunchesUseCase()
        val upcomingLaunches = useCaseWrapper.getUpcomingLaunchesUseCase()
    }
