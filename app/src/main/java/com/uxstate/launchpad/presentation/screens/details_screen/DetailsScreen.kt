package com.uxstate.launchpad.presentation.screens.details_screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.details_screen.components.DetailsImage
import com.uxstate.launchpad.presentation.screens.details_screen.components.LaunchBottomSheet
import com.uxstate.launchpad.util.LocalSpacing

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(launch: Launch, viewModel: DetailsViewModel = hiltViewModel()) {

    val probability by viewModel.probability.collectAsState()
    val spacing = LocalSpacing.current
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    BottomSheetScaffold(
        drawerGesturesEnabled = true,
        scaffoldState = scaffoldState,
        sheetPeekHeight = (spacing.spaceExtraLarge * 2.5f),

        // bottom sheet content
        sheetContent = {

            LaunchBottomSheet(
                probability = probability,
                launch = launch,
                onClickViewMap = { latitude, longitude -> }
            )
        }
    )

    // underlying stuff
    {
        DetailsImage(launch = launch)
    }
}