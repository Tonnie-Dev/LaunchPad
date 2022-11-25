package com.uxstate.launchpad.presentation.screens.home_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.TimeBoard
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun TimeBoardWidget(modifier: Modifier = Modifier, timeBoard: TimeBoard) {

    val spacing = LocalSpacing.current
    Row(modifier = modifier.padding(spacing.spaceExtraSmall)) {
    }
}

@Composable
fun TimeSlot(timeUnit: Int) {
    val spacing = LocalSpacing.current
    Column() {
        Text(
            text = stringResource(id = R.string.time_slot, timeUnit),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(spacing.spaceExtraSmall)
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun TimeSlotPreviewLight() {

    TimeSlot(timeUnit = 12)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TimeSlotPreviewDark() {

    TimeSlot(timeUnit = 7)
}