package com.uxstate.launchpad.presentation.screens.common

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.TimeBoard
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.util.LocalSpacing
import com.uxstate.launchpad.util.addAnimation

@Composable
fun TimeBoardWidget(modifier: Modifier = Modifier, timeBoard: TimeBoard) {

    val spacing = LocalSpacing.current

    Row(
        modifier = modifier.padding(spacing.spaceExtraSmall),

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

@OptIn(ExperimentalAnimationApi::class)
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
                style = MaterialTheme.typography.h5,

            )
            AnimatedContent(
                targetState = timeValue,
                transitionSpec = { addAnimation().using(SizeTransform(clip = true)) }
            ) {

                value ->
                Text(
                    text = stringResource(id = R.string.time_slot, value),
                    style = MaterialTheme.typography.h5,

                )
            }
        }

        Text(
            text = timeType,
            style = MaterialTheme.typography.overline,
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
    TimeBoardWidget(
        timeBoard = TimeBoard(
            days = 8,
            hours = 18,
            minutes = 12,
            seconds = 32
        )
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun TimeSlotPreviewLight() {

    TimeSlot(timeValue = 12, timeType = "Days")
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TimeSlotPreviewDark() {

    LaunchPadTheme() {
        TimeSlot(timeValue = 7, timeType = "Min")
    }
}
