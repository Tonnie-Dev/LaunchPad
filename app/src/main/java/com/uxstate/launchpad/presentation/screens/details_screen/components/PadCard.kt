package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun PadCard(
    launch: Launch,
    onClickViewMap: (Double, Double) -> Unit,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    Card(elevation = spacing.spaceExtraSmall, modifier = modifier) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = launch.pad.locationName,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = launch.pad.complex,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                PadCounter(
                    value = launch.pad.totalLaunchCount,
                    countDesc = stringResource(R.string.total_launches_text)
                )

                PadCounter(
                    value = launch.pad.totalLandingCount,
                    countDesc = stringResource(R.string.landing_count_text)
                )

                PadCounter(
                    value = launch.pad.total,
                    countDesc = stringResource(R.string.total_text)
                )
            }

            Button(onClick = {
                onClickViewMap(
                    launch.pad.latitude.toDouble(),
                    launch.pad.latitude.toDouble()
                )
            }) {

                Text(text = stringResource(R.string.view_map_button_text))
            }
        }
    }
}

@Composable
fun PadCounter(value: Int, countDesc: String, modifier: Modifier = Modifier) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value.toString(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            color = Color.Magenta
        )

        Text(
            text = countDesc,
            style = MaterialTheme.typography.caption,

        )
    }
}

@Preview
@Composable
fun PadCounterPreview() {
    PadCounter(value = 13, countDesc = stringResource(id = R.string.total_launches_text))
}


