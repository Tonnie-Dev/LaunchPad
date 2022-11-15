package com.uxstate.launchpad.presentation.screens.home_screen

import androidx.lifecycle.ViewModel
import com.uxstate.launchpad.domain.use_cases.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCaseWrapper: UseCaseWrapper
) : ViewModel() {

    val previousLaunches = useCaseWrapper.getPreviousLaunchesUseCase()
}