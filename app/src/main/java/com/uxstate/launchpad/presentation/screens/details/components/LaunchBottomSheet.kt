package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.generateLaunch

@Composable
fun LaunchBottomSheet(
    launch: Launch,
    onClickViewMap: (Double, Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
            modifier = modifier.verticalScroll(state = rememberScrollState())
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

            LaunchBottomSheet(
                    launch = generateLaunch(),
                    onClickViewMap = { _, _ -> }
            )

        }
    }
}
