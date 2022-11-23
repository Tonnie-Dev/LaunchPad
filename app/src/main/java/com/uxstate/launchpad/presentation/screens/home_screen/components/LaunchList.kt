package com.uxstate.launchpad.presentation.screens.home_screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimerState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LaunchList(
    data: LazyPagingItems<Launch>,
    showCountDown: Boolean,
    modifier: Modifier = Modifier,
    state: TimerState = TimerState(0),

) {

    LazyColumn(modifier = modifier, content = {

        items(data) { launch ->

            launch?.let {

                LaunchImage(launch = it, showCountDown = showCountDown, state = state)
            }
        }
    })
}