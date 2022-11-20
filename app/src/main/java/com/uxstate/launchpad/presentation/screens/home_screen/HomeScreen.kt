package com.uxstate.launchpad.presentation.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.uxstate.launchpad.presentation.screens.home_screen.components.Tabs
import com.uxstate.launchpad.presentation.screens.home_screen.components.TabsContent
import com.uxstate.launchpad.presentation.screens.home_screen.components.TopBar
import com.uxstate.launchpad.presentation.screens.home_screen.components.tabs.TabItem
import com.uxstate.launchpad.util.LocalSpacing

@OptIn(ExperimentalPagerApi::class)
@Destination()
@RootNavGraph(start = true)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val previousLaunches = viewModel.previousLaunches.collectAsLazyPagingItems()
    val upcomingLaunches = viewModel.upcomingLaunches.collectAsLazyPagingItems()
    val spacing = LocalSpacing.current

    val tabs = listOf(
        TabItem.Upcoming(data = upcomingLaunches),
        TabItem.Previous(data = previousLaunches)
    )

    val pagerState = rememberPagerState()

    Scaffold(topBar = { TopBar() }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}
