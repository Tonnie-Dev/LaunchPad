package com.uxstate.launchpad.presentation.screens.home_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.uxstate.launchpad.R

typealias ComposableFun = @Composable () -> Unit


sealed class TabItem(
    @DrawableRes var icon: Int,
    var title: String,
    var tab: ComposableFun
) {

    object Upcoming : TabItem(
            R.drawable.hourglass_svg,
            "UPCOMING",
            { LaunchList(data = null) }
    )

    object Previous : TabItem(
            R.drawable.rocket_svg,
            "PREVIOUS",
            { LaunchList(data = null) }
    )
}
