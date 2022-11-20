package com.uxstate.launchpad.presentation.screens.home_screen.components.tabs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun TopBar() {

    val spacing = LocalSpacing.current
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = spacing.spaceSmall,
        content = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = MaterialTheme.typography.h5.fontSize
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPrevLight() {
    LaunchPadTheme {
        TopBar()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarPrevDark() {
    LaunchPadTheme {
        TopBar()
    }
}