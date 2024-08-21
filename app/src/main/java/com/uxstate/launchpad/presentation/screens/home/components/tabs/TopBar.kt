package com.uxstate.launchpad.presentation.screens.home.components.tabs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.R
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            )
        },
    )
}

@PreviewLightDark
@Composable
fun TopBarPrevLight() {
    LaunchPadTheme {
        Surface {
            TopBar()
        }
    }
}
