package com.uxstate.launchpad.presentation.screens.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimeBoard
import com.uxstate.launchpad.domain.model.TimerState
import com.uxstate.launchpad.domain.model.computeTimeBoard
import com.uxstate.launchpad.presentation.screens.details.components.StatusSlot
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.LocalSpacing
import com.uxstate.launchpad.utils.addAnimation
import com.uxstate.launchpad.utils.formatLaunchDatabaseStringDate
import com.uxstate.launchpad.utils.generateLaunch
import com.uxstate.launchpad.utils.generateSecondsFlow


@Composable
fun TimeBoardWidget(launch: Launch, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    val secondsFlow by launch.generateSecondsFlow()
            .collectAsState(initial = 0)

    val timerState = TimerState(secondsFlow)
    val timeBoard = timerState.computeTimeBoard()
    Card(
            elevation = CardDefaults.cardElevation(
                    defaultElevation = spacing.spaceSmall
            ),
            modifier = modifier.padding(spacing.spaceSmall)
    ) {
        Column(
                modifier = Modifier.padding(
                        horizontal = spacing.spaceMedium,
                        vertical = spacing.spaceSmall
                )

        ) {
            TimeSlotsRow(
                    timeBoard = timeBoard,
                    modifier = Modifier
                            .fillMaxWidth()

            )
            Text(
                    text = (launch.startWindowDate).formatLaunchDatabaseStringDate(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                            .fillMaxWidth()
            )

            StatusSlot(launch)
        }
    }
}

@Composable
fun TimeSlotsRow(
    modifier: Modifier = Modifier,
    timeBoard: TimeBoard
) {

    val spacing = LocalSpacing.current

    Row(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceExtraSmall),
            horizontalArrangement = Arrangement.Center

    ) {

        TimeSlot(
                timeValue = timeBoard.days,
                prefix = stringResource(id = R.string.t_minus_label),
                timeType = stringResource(R.string.time_label)
        )

        TimeSlot(
                timeValue = timeBoard.hours,
                prefix = stringResource(id = R.string.time_colon_separator),
                timeType = stringResource(R.string.hrs_abbrev)
        )

        TimeSlot(
                timeValue = timeBoard.minutes,
                prefix = stringResource(id = R.string.time_colon_separator),
                timeType = stringResource(R.string.min_abbrev)
        )

        TimeSlot(
                timeValue = timeBoard.seconds,
                prefix = stringResource(id = R.string.time_colon_separator),
                timeType = stringResource(R.string.sec_abbrev)
        )
    }
}

@Composable
fun TimeSlot(timeValue: Int, timeType: String, prefix: String = "") {
    val spacing = LocalSpacing.current
    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .padding(spacing.spaceDoubleDp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                    text = prefix,
                    style = MaterialTheme.typography.headlineSmall

            )
            AnimatedContent(
                    targetState = timeValue,
                    transitionSpec = { addAnimation().using(SizeTransform(clip = true)) }
            ) {

                value ->
                Text(
                        text = stringResource(id = R.string.time_slot, value),
                        style = MaterialTheme.typography.headlineSmall

                )
            }
        }

        Text(
                text = timeType,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.End,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceExtraSmall)
        )
    }
}

@Preview
@Composable
fun TimeBoardPreview() {
    TimeSlotsRow(
            timeBoard = TimeBoard(
                    days = 8,
                    hours = 18,
                    minutes = 12,
                    seconds = 32
            )
    )
}


@PreviewLightDark
@Composable

private fun TimeSlotPreviewDark() {

    LaunchPadTheme {
        Surface {

            TimeSlot(timeValue = 7, timeType = "Min")
        }
    }
}


@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun TimeBoardWidgetPreview() {
    LaunchPadTheme {
        Surface {
            TimeBoardWidget(launch = generateLaunch())
        }
    }
}
