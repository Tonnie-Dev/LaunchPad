package com.uxstate.launchpad.presentation.screens.home.components.tabs

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.home.components.LaunchList

sealed class TabItem(
    @DrawableRes
    val icon: Int,
    val title: String,
    val composeFunction: @Composable () -> Unit
) {

    data class Upcoming(
        val data: LazyPagingItems<Launch>,
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
