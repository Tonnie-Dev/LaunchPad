package com.uxstate.launchpad.presentation.screens.details.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.common.SimpleAlertDialog
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.generateLaunch
import com.uxstate.launchpad.utils.openGoogleMap

@Composable
fun LaunchBottomSheet(
    launch: Launch,
    isShowDialog: Boolean,
    context: Context,
    onDismissDialog: () -> Unit,
    onConfirmDialog: () -> Unit,
    onShowDialog: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SimpleAlertDialog(
        isShowDialog = isShowDialog,
        onDismiss = onDismissDialog,
        onConfirm = onConfirmDialog,
    )
    LaunchBottomSheetContent(
        modifier = modifier,
        launch = launch,
        onClickViewMap = { latitude, longitude ->

            if (latitude == 0.0 || longitude == 0.0) {
                onShowDialog()
            } else {
                openGoogleMap(latitude, longitude, context)
            }
        },
    )
}

@Composable
fun LaunchBottomSheetContent(
    launch: Launch,
    onClickViewMap: (Double, Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(state = rememberScrollState()),
    ) {
        StatusSection(launch = launch)

        MissionSection(launch = launch)

        PadCard(launch = launch, onClickViewMap = onClickViewMap)
    }
}

@PreviewLightDark
@Composable
private fun LaunchBottomSheetContentPreview() {
    LaunchPadTheme {
        Surface {
            LaunchBottomSheetContent(
                launch = generateLaunch(),
                onClickViewMap = { _, _ -> },
            )
        }
    }
}
