package com.uxstate.launchpad.presentation.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun LaunchTopBar(text: String, modifier: Modifier = Modifier, onClickBackArrow: () -> Unit) {
    val spacing = LocalSpacing.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(spacing.spaceExtraLarge)
            .fillMaxWidth()
            .padding(spacing.spaceSmall)
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back_label),
            modifier = Modifier.clickable {
                onClickBackArrow()
            }
        )
        Text(
            text = "   $text",
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun TopBarPreview() {

    LaunchTopBar(text = "Falcon", onClickBackArrow = {})
}