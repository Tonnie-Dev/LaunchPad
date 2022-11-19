package com.uxstate.launchpad.presentation.screens.home_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.uxstate.launchpad.presentation.screens.home_screen.components.TabItem
import com.uxstate.launchpad.util.LocalSpacing

@OptIn(ExperimentalPagerApi::class)
@Destination()
@RootNavGraph(start = true)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val previousLaunches = viewModel.previousLaunches.collectAsLazyPagingItems()
    val upcomingLaunches = viewModel.upcomingLaunches.collectAsLazyPagingItems()
    val spacing = LocalSpacing.current

    val tabs = listOf(TabItem.Upcoming, TabItem.Previous)

    val pagerState = rememberPagerState()
}
