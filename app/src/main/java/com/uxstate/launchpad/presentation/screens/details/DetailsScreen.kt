package com.uxstate.launchpad.presentation.screens.details

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.common.LaunchTopBar
import com.uxstate.launchpad.presentation.screens.common.SimpleAlertDialog
import com.uxstate.launchpad.presentation.screens.destinations.FullPhotoScreenDestination
import com.uxstate.launchpad.presentation.screens.details.components.BackgroundContent
import com.uxstate.launchpad.presentation.screens.details.components.LaunchBottomSheet
import com.uxstate.launchpad.utils.LocalSpacing
import com.uxstate.launchpad.utils.openGoogleMap

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(
    launch: Launch,
    viewModel: DetailsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val probability by viewModel.probability.collectAsState()

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val isShowDialog by viewModel.isShowDialog.collectAsState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = (spacing.spaceExtraLarge * 2.5f),
        topBar = {
            LaunchTopBar(
                text = launch.name,
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
        BackgroundContent(
            launch = launch,
            imageFractionHeight = scaffoldState.currentSheetFraction,
            onShowFullScreen = {
                navigator.popBackStack()
                navigator.navigate(FullPhotoScreenDestination(launch = launch))
            },
            onClose = { navigator.navigateUp() }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue
                == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue
                == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue
                == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue
                == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }
