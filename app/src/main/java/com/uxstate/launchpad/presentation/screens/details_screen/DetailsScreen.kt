package com.uxstate.launchpad.presentation.screens.details_screen

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.common.SimpleAlertDialog
import com.uxstate.launchpad.presentation.screens.details_screen.components.DetailsImage
import com.uxstate.launchpad.presentation.screens.details_screen.components.DetailsTopBar
import com.uxstate.launchpad.presentation.screens.details_screen.components.LaunchBottomSheet
import com.uxstate.launchpad.util.LocalSpacing
import com.uxstate.launchpad.util.openGoogleMap
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(
    launch: Launch,
    viewModel: DetailsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val isShowDialog by viewModel.isShowDialog.collectAsState()


    val probability by viewModel.probability.collectAsState()
    val spacing = LocalSpacing.current
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val context = LocalContext.current
    BottomSheetScaffold(
        drawerGesturesEnabled = true,
        scaffoldState = scaffoldState,
        sheetPeekHeight = (spacing.spaceExtraLarge * 2.5f),
        topBar = {
            DetailsTopBar(
                launch = launch,
                onClickBackArrow = { navigator.navigateUp() }
            )
        },
        // bottom sheet content
        sheetContent = {
            SimpleAlertDialog(
                isShowDialog = isShowDialog,
                onDismiss = viewModel::onDialogDismiss,
                onConfirm = viewModel::onDialogConfirm
            )
            LaunchBottomSheet(
                probability = probability,
                launch = launch,
                onClickViewMap = { latitude, longitude ->
                    Timber.i(
                        "The lat is $latitude, lon is" +
                            " $longitude  sum is ${latitude + longitude}"
                    )

                    if (latitude == 0.0 || longitude == 0.0) {

                        viewModel.onDialogShow()
                    } else {

                        openGoogleMap(latitude, longitude, context)
                    }
                }
            )

                 }

    )

    // underlying stuff
    {
        DetailsImage(launch = launch)
    }
}