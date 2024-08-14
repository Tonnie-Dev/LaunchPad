package com.uxstate.launchpad.presentation.screens.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.LocalSpacing


@Composable
fun RocketInfoRow(launch: Launch, modifier: Modifier = Modifier) {


    val rocketIcons = listOf(
            RocketIconDataClass(
                    itemText = stringResource(R.string.rocket_label),
                    value = launch.rocket.name,
                    icon = R.drawable.rocket_icon
            ),
            RocketIconDataClass(
                    itemText = stringResource(R.string.family_label),
                    value = launch.rocket.family,
                    icon = R.drawable.flight_icon
            ),
            RocketIconDataClass(
                    itemText = stringResource(R.string.agency_label),
                    value = launch.provider.name,
                    icon = R.drawable.flag_icon
            ),
            RocketIconDataClass(
                    itemText = stringResource(R.string.type_label),
                    value = launch.provider.type,
                    icon = R.drawable.satellite_icon
            )
    )


}

data class RocketIconDataClass(val itemText: String, val value: String, @DrawableRes val icon: Int)

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


    ) {


        Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(spacing.spaceExtraSmall)
        ) {
            Box(
                    modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary)
                            .padding(spacing.spaceExtraSmall),
                    contentAlignment = Alignment.Center

            ) {
                Icon(
                        painter = painterResource(id = icon),
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
                        overflow = TextOverflow.Ellipsis
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
                icon = R.drawable.rocket_icon
        )

    }

}