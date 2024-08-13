package com.uxstate.launchpad.presentation.screens.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.R
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
        elevation = CardDefaults.cardElevation(defaultElevation = spacing.spaceExtraSmall),
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
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = itemText,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Black,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@PreviewLightDark
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