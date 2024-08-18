package com.uxstate.launchpad.presentation.screens.details

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitScreen
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.common.LaunchTopBar
import com.uxstate.launchpad.presentation.screens.destinations.FullPhotoScreenDestination
import com.uxstate.launchpad.presentation.screens.details.components.BackgroundContent
import com.uxstate.launchpad.presentation.screens.details.components.LaunchBottomSheet
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.generateLaunch

@Destination
@Composable
fun DetailsScreen(
    launch: Launch,
    viewModel: DetailsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val isShowDialog by viewModel.isShowDialog.collectAsState()

    DetailScreenContent(
            launch = launch,
            isShowDialog = isShowDialog,
            onShowDialog = viewModel::onDialogShow,
            onConfirmDialog = viewModel::onConfirmDialog,
            onDismissDialog = viewModel::onDismissDialog,
            onNavigateUp = { navigator.navigateUp() },
            onNavigateToFullScreen = { navigator.navigate(it) },
            onPopBackStack = { navigator.popBackStack() }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    launch: Launch,
    isShowDialog: Boolean,
    onShowDialog: () -> Unit,
    onConfirmDialog: () -> Unit,
    onDismissDialog: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToFullScreen: (Direction) -> Unit,
    onPopBackStack: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    val scaffoldState = rememberBottomSheetScaffoldState()
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues()
            .calculateBottomPadding()
    BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = BottomSheetDefaults.SheetPeekHeight + navigationBarHeight,
            topBar = {
                LaunchTopBar(
                        text = launch.name,
                        onClickBackArrow = onNavigateUp,
                        action = {
                            IconButton(onClick = {
                                onPopBackStack()
                                onNavigateToFullScreen(FullPhotoScreenDestination(launch = launch))

                            }) {
                                Icon(
                                        imageVector = Icons.Filled.FitScreen,
                                        contentDescription = ""
                                )

                            }
                        }
                )
            },
            // bottom sheet content
            sheetContent = {
                LaunchBottomSheet(
                        launch = launch,
                        isShowDialog = isShowDialog,
                        context = context,
                        onDismissDialog = onDismissDialog,
                        onConfirmDialog = onConfirmDialog,
                        onShowDialog = onShowDialog,
                        modifier = modifier

                )
            }

    )


    {
        BackgroundContent(launch = launch, modifier = modifier.padding(it))
    }
}

@PreviewLightDark
@Composable
private fun DetailScreenContentPreview() {

    LaunchPadTheme {
        DetailScreenContent(
                launch = generateLaunch(),
                isShowDialog = false,
                onShowDialog = {},
                onPopBackStack = {},
                onNavigateUp = {},
                onNavigateToFullScreen = {},
                onConfirmDialog = {},
                onDismissDialog = {},
                modifier = Modifier.fillMaxSize()
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@PreviewLightDark
@Composable
private fun DetailScreenContentShowDialogPreview() {

    LaunchPadTheme {
        DetailScreenContent(
                launch = generateLaunch(),
                isShowDialog = false,
                onShowDialog = {},
                onPopBackStack = {},
                onNavigateUp = {},
                onNavigateToFullScreen = {},
                onConfirmDialog = {},
                onDismissDialog = {},
                modifier = Modifier.fillMaxSize()
        )
    }

}

/*@OptIn(ExperimentalMaterial3Api::class)
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
    }*/
