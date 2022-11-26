package com.uxstate.launchpad.presentation.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.presentation.screens.home_screen.components.tabs.TabItem
import com.uxstate.launchpad.presentation.screens.home_screen.components.tabs.Tabs
import com.uxstate.launchpad.presentation.screens.home_screen.components.tabs.TabsContent
import com.uxstate.launchpad.presentation.screens.home_screen.components.tabs.TopBar

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Destination()
@RootNavGraph(start = true)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navigator: DestinationsNavigator) {

    val previousLaunches = viewModel.previousLaunches.collectAsLazyPagingItems()
    val upcomingLaunches = viewModel.upcomingLaunches.collectAsLazyPagingItems()
    val pagerState = rememberPagerState()

    val tabs = listOf(
        TabItem.Upcoming(data = upcomingLaunches, navigator = navigator),
        TabItem.Previous(data = previousLaunches, navigator = navigator)
    )

    Scaffold(topBar = { TopBar() }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}
