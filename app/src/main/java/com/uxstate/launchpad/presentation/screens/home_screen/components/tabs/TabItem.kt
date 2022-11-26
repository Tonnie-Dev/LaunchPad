package com.uxstate.launchpad.presentation.screens.home_screen.components.tabs

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimerState
import com.uxstate.launchpad.presentation.screens.home_screen.components.LaunchList



sealed class TabItem(
    @DrawableRes
    val icon: Int,
    val title: String,
    val composeFunction: @Composable () -> Unit
) {

    @RequiresApi(Build.VERSION_CODES.O)
    data class Upcoming(
        val data: LazyPagingItems<Launch>,
        val state: TimerState,
        val navigator: DestinationsNavigator,
    ) : TabItem(
            icon = R.drawable.hourglass,
            title = "UPCOMING",
            composeFunction = {
                LaunchList(
                        data = data,
                        showCountDown = true,
                        navigator = navigator
                )
            }
    )

    @RequiresApi(Build.VERSION_CODES.O)
    data class Previous(
        val data: LazyPagingItems<Launch>,
        val navigator: DestinationsNavigator
    ) : TabItem(
            icon = R.drawable.rocket_icon,
            title = "PREVIOUS",
            composeFunction = {
                LaunchList(
                        data = data,
                        showCountDown = false,
                        navigator = navigator
                )
            }
    )
}
