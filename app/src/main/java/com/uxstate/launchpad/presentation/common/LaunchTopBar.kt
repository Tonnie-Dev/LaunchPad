package com.uxstate.launchpad.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchTopBar(
    text: String,
    onClickBackArrow: () -> Unit,
    modifier: Modifier = Modifier,
    action: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_label),
                modifier =
                    Modifier.clickable {
                        onClickBackArrow()
                    },
            )
        },
        title = {
            Text(
                text = "   $text",
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth(),
            )
        },
        actions = { action?.invoke() },
    )
}

@PreviewLightDark
@Composable
private fun TopBarPreview() {
    LaunchTopBar(
        text = "Falcon",
        onClickBackArrow = {},
        action = {
            Icon(
                imageVector = Icons.Default.Fullscreen,
                contentDescription = "",
            )
        },
    )
}
