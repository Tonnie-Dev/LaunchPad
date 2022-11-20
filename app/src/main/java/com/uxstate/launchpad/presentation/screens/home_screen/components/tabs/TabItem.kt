package com.uxstate.launchpad.presentation.screens.home_screen.components.tabs

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.home_screen.components.LaunchList

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(
    @DrawableRes val icon: Int,
    val title: String,
    // val screen: ComposableFun,
    val compos: @Composable () -> Unit
) {

    @RequiresApi(Build.VERSION_CODES.O)
    data class Upcoming(val data: LazyPagingItems<Launch>) : TabItem(
        icon = R.drawable.hourglass,
        title = "UPCOMING",
        compos = { LaunchList(data = data) }
    )

    @RequiresApi(Build.VERSION_CODES.O)
    data class Previous(val data: LazyPagingItems<Launch>) : TabItem(
        R.drawable.rocket_icon,
        "PREVIOUS",
        { LaunchList(data = data) }
    )
}
