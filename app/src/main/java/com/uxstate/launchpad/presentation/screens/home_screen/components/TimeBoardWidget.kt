package com.uxstate.launchpad.presentation.screens.home_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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

@Composable
fun TimeBoardWidget(modifier: Modifier = Modifier, timeBoard: TimeBoard) {

    val spacing = LocalSpacing.current

    Row(
        modifier = modifier.padding(spacing.spaceExtraSmall),

    ) {

        /*Text(
                text = stringResource(R.string.t_minus_label),
                style = MaterialTheme.typography.h6,

                )*/
        TimeSlot(
            timeValue = timeBoard.days,
            timeType = stringResource(R.string.time_label),
            tMinus = stringResource(id = R.string.t_minus_label)
        )
        /* Text(
                 text = stringResource(R.string.time_colon_gap),
                 style = MaterialTheme.typography.h6,
                 modifier = Modifier
                         .width(intrinsicSize = IntrinsicSize.Min)
                         .alignBy(LastBaseline)

         )*/
        TimeSlot(
            timeValue = timeBoard.hours,
            timeType = stringResource(R.string.hrs_abbrev),
            colonSeparator = stringResource(id = R.string.time_colon_separator)
        )
        // Text(text = stringResource(R.string.time_colon_gap), style = MaterialTheme.typography.h6)
        TimeSlot(
            timeValue = timeBoard.minutes,
            timeType = stringResource(R.string.min_abbrev),
            colonSeparator = stringResource(id = R.string.time_colon_separator)
        )
        // Text(text = stringResource(R.string.time_colon_separator), style = MaterialTheme.typography.h6

        TimeSlot(timeValue = timeBoard.seconds, timeType = stringResource(R.string.sec_abbrev))
    }
}

@Composable
fun TimeSlot(timeValue: Int, timeType: String, colonSeparator: String = "", tMinus: String = "") {
    val spacing = LocalSpacing.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(spacing.spaceDoubleDp)
    ) {
        Text(
            text = stringResource(id = R.string.time_slot, tMinus, timeValue, colonSeparator),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(spacing.spaceExtraSmall)
        )

        Text(
            text = timeType,
            style = MaterialTheme.typography.overline,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun TimeBoardPreview() {
    TimeBoardWidget(timeBoard = TimeBoard(days = 8, hours = 18, minutes = 12, seconds = 32))
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