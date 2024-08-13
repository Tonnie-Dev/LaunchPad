package com.uxstate.launchpad.presentation.screens.details


import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
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

@OptIn( ExperimentalMaterial3Api::class)
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


    val sheetState = rememberModalBottomSheetState()
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
@OptIn(ExperimentalMaterial3Api::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    @Composable
    get() {
        val fraction by remember(bottomSheetState) {
            derivedStateOf {
                runCatching { bottomSheetState.requireOffset() }.getOrDefault(0F)
            }
        }
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == SheetValue.Hidden && targetValue == SheetValue.Hidden -> 1f
            currentValue == SheetValue.Expanded && targetValue == SheetValue.Expanded -> 0f
            currentValue == SheetValue.Hidden && targetValue == SheetValue.Expanded -> 1f - fraction
            currentValue == SheetValue.Expanded && targetValue == SheetValue.Hidden -> fraction
            else -> fraction
        }
    }
