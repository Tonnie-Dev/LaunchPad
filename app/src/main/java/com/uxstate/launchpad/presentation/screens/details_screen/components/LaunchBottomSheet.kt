package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimerState
import com.uxstate.launchpad.domain.model.computeTimeBoard
import com.uxstate.launchpad.presentation.screens.common.TimeBoardWidget
import com.uxstate.launchpad.presentation.ui.theme.DahliaYellow
import com.uxstate.launchpad.presentation.ui.theme.LuminousRed
import com.uxstate.launchpad.presentation.ui.theme.MintGreen
import com.uxstate.launchpad.presentation.ui.theme.OysterWhite
import com.uxstate.launchpad.util.LocalSpacing
import com.uxstate.launchpad.util.formatLaunchDatabaseStringDate
import com.uxstate.launchpad.util.generateSecondsFlow

@Composable
fun LaunchBottomSheet(
    probability: Int,
    launch: Launch,
    onClickViewMap: (Double, Double) -> Unit,
    modifier: Modifier = Modifier
) {
    val secondsFlow by launch.generateSecondsFlow()
        .collectAsState(initial = 0)

    val timerState = TimerState(secondsFlow)
    val timeBoard = timerState.computeTimeBoard()

    val backgroundColor = when (launch.status.abbrev) {

        "Success" -> MintGreen
        "Go" -> MintGreen
        "TBD" -> DahliaYellow
        "Failure" -> LuminousRed
        else -> OysterWhite
    }

    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
            .fillMaxWidth()
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

                    ProbabilityCircle(
                        probability = probability,
                            /*  modifier = Modifier
                                  .size(spacing.spaceExtraLarge + spacing.spaceLarge)
                                  .padding(spacing.spaceExtraSmall)*/
                    )
                }
            }
            // c2 - Timeboard

            Column(modifier = Modifier.weight(.65f)) {

                Card(
                    elevation = spacing.spaceExtraSmall,
                    modifier = Modifier.padding(spacing.spaceSmall)
                ) {
                    Column(modifier = Modifier.padding(spacing.spaceExtraSmall)) {
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

                        Spacer(modifier = Modifier.height(spacing.spaceMedium + spacing.spaceSmall))
                        Text(
                            text = (launch.status.name),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(CutCornerShape(spacing.spaceExtraSmall))
                                .background(color = backgroundColor)
                        )
                    }
                }
            }
        }

        // status
        StatusSection(launch = launch)

        MissionSection(launch = launch)

        PadCard(launch = launch, onClickViewMap = onClickViewMap)
    }
}
