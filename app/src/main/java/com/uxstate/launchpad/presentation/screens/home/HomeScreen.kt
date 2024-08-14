package com.uxstate.launchpad.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.presentation.screens.home.components.tabs.TabItem
import com.uxstate.launchpad.presentation.screens.home.components.tabs.Tabs
import com.uxstate.launchpad.presentation.screens.home.components.tabs.TabsContent
import com.uxstate.launchpad.presentation.screens.home.components.tabs.TopBar


@Destination()
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navigator: DestinationsNavigator) {

    val previousLaunches = viewModel.previousLaunches.collectAsLazyPagingItems()
    val upcomingLaunches = viewModel.upcomingLaunches.collectAsLazyPagingItems()

    val tabs = listOf(
        TabItem.Upcoming(data = upcomingLaunches, navigator = navigator),
        TabItem.Previous(data = previousLaunches, navigator = navigator)
    )

    val pagerState = rememberPagerState {tabs.size}

    Scaffold(topBar = { TopBar() }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}
