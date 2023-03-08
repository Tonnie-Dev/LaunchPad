package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimerState
import com.uxstate.launchpad.domain.model.computeTimeBoard
import com.uxstate.launchpad.presentation.screens.common.TimeBoardWidget
import com.uxstate.launchpad.utils.LocalSpacing
import com.uxstate.launchpad.utils.formatLaunchDatabaseStringDate
import com.uxstate.launchpad.utils.generateSecondsFlow

@Composable
fun LaunchBottomSheet(
    probability: Int,
    launch: Launch,
    onClickViewMap: (Double, Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    val secondsFlow by launch.generateSecondsFlow()
        .collectAsState(initial = 0)

    val timerState = TimerState(secondsFlow)
    val timeBoard = timerState.computeTimeBoard()

    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .fillMaxHeight(.75f)
            .padding(spacing.spaceSmall)

    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // c1 - Probability Circle

            Column(
                modifier = Modifier
                    .weight(.35f)
                    .fillMaxWidth()
            ) {
                Card(
                    elevation = spacing.spaceExtraSmall,
                    modifier = Modifier.padding(spacing.spaceSmall)
                ) {

                    ProbabilityCircle(probability = probability)
                }
            }
            // c2 - Timeboard

            Column(modifier = Modifier.weight(.65f)) {

                Card(
                    elevation = spacing.spaceExtraSmall,
                    modifier = Modifier.padding(spacing.spaceSmall)
                ) {
                    Column(
                        modifier = Modifier.padding(spacing.spaceExtraSmall)

                    ) {
                        TimeBoardWidget(
                            timeBoard = timeBoard,
                            modifier = Modifier
                                .fillMaxWidth()

                        )
                        Text(
                            text = (launch.startWindowDate).formatLaunchDatabaseStringDate(),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        StatusSlot(launch)
                    }
                }
            }
        }

        Column(
            modifier = Modifier.verticalScroll(
                state =
                rememberScrollState()
            )
        ) {
            // status
            StatusSection(launch = launch)

            MissionSection(launch = launch)

            PadCard(launch = launch, onClickViewMap = onClickViewMap)
        }
    }
}