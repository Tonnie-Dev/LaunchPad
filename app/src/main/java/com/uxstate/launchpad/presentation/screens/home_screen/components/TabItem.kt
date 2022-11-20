package com.uxstate.launchpad.presentation.screens.home_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(
    @DrawableRes val icon: Int,
    val title: String,
    val screen: ComposableFun,

) {

    data class Upcoming(val data: LazyPagingItems<Launch>) : TabItem(
        icon = R.drawable.hourglass,
        title = "UPCOMING",
        screen = { LaunchList(data = data) }
    )

    data class Previous(val data: LazyPagingItems<Launch>) : TabItem(
        R.drawable.rocket_icon,
        "PREVIOUS",
        { LaunchList(data = data) }
    )
}
