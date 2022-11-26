package com.uxstate.launchpad.presentation.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.uxstate.launchpad.domain.use_cases.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel @Inject constructor(
    useCaseWrapper: UseCaseWrapper
) : ViewModel() {

    val previousLaunches = useCaseWrapper.getPreviousLaunchesUseCase()
    val upcomingLaunches = useCaseWrapper.getUpcomingLaunchesUseCase()
}