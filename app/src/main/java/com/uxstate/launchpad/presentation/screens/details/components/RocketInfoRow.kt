package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.SatelliteAlt
import androidx.compose.material.icons.filled.TempleBuddhist
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.LocalSpacing
import com.uxstate.launchpad.utils.capitalizeFirstLetter
import com.uxstate.launchpad.utils.generateLaunch


@Composable
fun RocketInfoRow(launch: Launch, modifier: Modifier = Modifier) {
    val spacing = LocalSpacing.current

    val rocketIcons = listOf(

            RocketIconDataClass(
                    itemText = stringResource(R.string.agency_label),
                    value = launch.provider.name,
                    icon = Icons.Default.Apartment
            ),
            RocketIconDataClass(
                    itemText = stringResource(R.string.rocket_label),
                    value = launch.rocket.name,
                    icon = Icons.Default.Rocket
            ),

            RocketIconDataClass(
                    itemText = stringResource(R.string.family_label),
                    value = launch.rocket.family.capitalizeFirstLetter(),
                    icon = Icons.Default.Anchor
            ),

            RocketIconDataClass(
                    itemText = stringResource(R.string.type_label),
                    value = launch.provider.type.capitalizeFirstLetter(),
                    icon = Icons.Default.SatelliteAlt
            )
    )

    Row(
            modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(spacing.spaceSmall),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
    ) {
        rocketIcons.forEach { icon ->
            RocketIcon(
                    itemText = icon.itemText,
                    value = icon.value,
                    icon = icon.icon,
                    modifier = Modifier.weight(1f)
            )
        }
    }


}

data class RocketIconDataClass(val itemText: String, val value: String, val icon: ImageVector)

@Composable
fun RocketIcon(
    itemText: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current

    Card(
            elevation = CardDefaults.cardElevation(defaultElevation = spacing.spaceSmall),
            modifier = modifier
    ) {


        Column(
                modifier = Modifier
                        .padding(spacing.spaceExtraSmall)
                        .align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                    modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary)
                            .padding(spacing.spaceExtraSmall),
                    contentAlignment = Alignment.Center

            ) {
                Icon(
                        imageVector = icon,
                        contentDescription = itemText,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                        text = itemText,
                        style = MaterialTheme.typography.labelSmall
                )
                Text(
                        text = value,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Black,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                )
            }
        }

    }
}


@PreviewLightDark
@Composable
fun RocketIconPreviewLight() {

    LaunchPadTheme {

        RocketIcon(
                itemText = "Family",
                value = "SpaceX",
                icon = Icons.Default.TempleBuddhist
        )

    }

}


@PreviewLightDark
@Composable
private fun RocketInfoRowPreview() {

    LaunchPadTheme {
        RocketInfoRow(launch = generateLaunch())
    }
}