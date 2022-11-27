package com.uxstate.launchpad.presentation.screens.details_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.details_screen.components.DetailsImage

@Destination
@Composable
fun DetailsScreen(launch: Launch, viewModel: DetailsViewModel = hiltViewModel()) {

    val probability by viewModel.probability.collectAsState()
    DetailsImage(
        launch = launch, probability = probability
    )
}