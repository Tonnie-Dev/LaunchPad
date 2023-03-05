package com.uxstate.launchpad.presentation.screens.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.LocalSpacing

@Composable
fun RocketIcon(
    itemText: String,
    value: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current

    Card(
        elevation = spacing.spaceExtraSmall,
        modifier = modifier
            .size(spacing.spaceExtraLarge + spacing.spaceLarge + spacing.spaceSmall)
            .padding(spacing.spaceSmall)

    ) {
        Column(
            modifier = Modifier.padding(spacing.spaceExtraSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = itemText,
                modifier = Modifier.size(spacing.spaceLarge + spacing.spaceMedium),
                tint = MaterialTheme.colors.secondary
            )
            Text(
                text = itemText,
                style = MaterialTheme.typography.overline
            )
            Text(
                text = value,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Black,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun RocketIconPreviewLight() {

    val spacing = LocalSpacing.current
    RocketIcon(
        itemText = "Family",
        value = "SpaceX",
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
            itemText = "Family",
            value = "SpaceX",
            icon = R.drawable.satellite_icon,
            modifier = Modifier.size(spacing.spaceLarge)
        )
    }
}
