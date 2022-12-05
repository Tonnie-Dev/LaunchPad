package com.uxstate.launchpad.presentation.screens.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.use_cases.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DetailsViewModel @Inject constructor(

    private val wrapper: UseCaseWrapper
) : ViewModel() {

    private val _probability = MutableStateFlow(Random.nextInt(50, 100))
    val probability = _probability.asStateFlow()

    private val _isShowDialog = MutableStateFlow(false)
    val isShowDialog = _isShowDialog.asStateFlow()

    
}