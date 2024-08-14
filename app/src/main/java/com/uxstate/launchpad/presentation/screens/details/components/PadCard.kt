package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
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
            elevation = CardDefaults.cardElevation(defaultElevation = spacing.spaceExtraSmall),
            modifier = modifier.padding(spacing.spaceSmall)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                    text = launch.pad.locationName,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
            )

            Text(
                    text = launch.pad.complex,
                    style = MaterialTheme.typography.bodyLarge,
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
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
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
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary
        )

        Text(
                text = countDesc,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Black
        )
    }
}


@PreviewLightDark
@Composable
private fun PadCounterPreview() {
    PadCounter(
            value = 13,
            countDesc = stringResource(id = R.string.total_launches_text)
    )
}