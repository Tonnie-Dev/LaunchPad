package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.utils.LocalSpacing

@Composable
fun PadCard(
    launch: Launch,
    onClickViewMap: (Double, Double) -> Unit,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    Card(
        elevation = spacing.spaceExtraSmall,
        modifier = modifier.padding(spacing.spaceSmall)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = launch.pad.locationName,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                maxLines = 2,
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

            Button(
                onClick = {

                    val latitude = launch.pad.latitude.toDouble()
                    val longitude = launch.pad.longitude.toDouble()

                    onClickViewMap(latitude, longitude)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    MaterialTheme.colors.secondary,
                    contentColor =
                    MaterialTheme.colors.onSecondary
                )
            ) {

                Text(text = stringResource(R.string.view_map_button_text))
            }
        }
    }
}

@Composable
fun PadCounter(value: Int, countDesc: String, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(spacing.spaceSmall)
    ) {
        Text(
            text = value.toString(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.secondary
        )

        Text(
            text = countDesc,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Black
        )
    }
}

@Preview
@Composable
fun PadCounterPreview() {
    PadCounter(value = 13, countDesc = stringResource(id = R.string.total_launches_text))
}
