package com.uxstate.launchpad.presentation.screens.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class DetailsViewModel
    @Inject
    constructor() : ViewModel() {
        private val _probability = MutableStateFlow(Random.nextInt(50, 100))
        val probability = _probability.asStateFlow()

        private val _isShowDialog = MutableStateFlow(false)
        val isShowDialog = _isShowDialog.asStateFlow()

        fun onDialogShow() {
            _isShowDialog.value = true
        }

        fun onConfirmDialog() {
            _isShowDialog.value = false
        }

        fun onDismissDialog() {
            _isShowDialog.value = false
        }
    }
