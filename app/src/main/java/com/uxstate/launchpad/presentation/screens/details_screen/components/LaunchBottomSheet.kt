package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.uxstate.launchpad.util.generateSecondsFlow

@Composable
fun LaunchBottomSheet(modifier: Modifier = Modifier, probability: Int, launch: Launch) {
    val secondsFlow by launch.generateSecondsFlow().collectAsState(initial = 0)
    val timerState = TimerState(secondsFlow)
    val timeBoard = timerState.computeTimeBoard()
    Column(modifier = modifier.fillMaxWidth()) {

        // name
        Text(
            text = launch.name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )

        Row() {
            // c1 - Probability Circle

            Column() {

                ProbabilityCircle(probability = probability)
            }
            // c2 - Timeboard

            Column() {
                TimeBoardWidget(timeBoard = timeBoard)
            }
        }

        // status
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = launch.status.name,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = launch.status.description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
