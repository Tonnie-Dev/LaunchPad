package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun RocketIcon(itemText: String, @DrawableRes icon: Int, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = itemText,
            modifier = Modifier.size(spacing.spaceExtraLarge),
            tint = Color.Magenta
        )

        Text(
            text = itemText,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Black
        )
    }
}

@Preview
@Composable
fun RocketIconPreviewLight() {

    val spacing = LocalSpacing.current
    RocketIcon(
        itemText = "Space X",
        icon = R.drawable.satellite_icon,
        modifier = Modifier.size(spacing.spaceLarge)
    )
}

@Preview
@Composable
fun RocketIconPreviewDark() {
    val spacing = LocalSpacing.current
    LaunchPadTheme {
        RocketIcon(
            itemText = "Space X",
            icon = R.drawable.satellite_icon,
            modifier = Modifier.size(spacing.spaceLarge)
        )
    }
}