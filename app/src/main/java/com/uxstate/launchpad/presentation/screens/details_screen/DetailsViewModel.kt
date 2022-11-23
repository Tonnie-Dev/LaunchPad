package com.uxstate.launchpad.presentation.screens.details_screen

import androidx.lifecycle.ViewModel
import com.uxstate.launchpad.domain.use_cases.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(wrapper: UseCaseWrapper) : ViewModel() {
}