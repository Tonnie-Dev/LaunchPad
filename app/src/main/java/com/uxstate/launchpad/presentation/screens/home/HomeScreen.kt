package com.uxstate.launchpad.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.presentation.screens.home.components.tabs.TabItem
import com.uxstate.launchpad.presentation.screens.home.components.tabs.Tabs
import com.uxstate.launchpad.presentation.screens.home.components.tabs.TabsContent
import com.uxstate.launchpad.presentation.screens.home.components.tabs.TopBar
import com.uxstate.launchpad.presentation.ui.theme.statusBarColor

@OptIn(ExperimentalPagerApi::class)
@Destination()
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navigator: DestinationsNavigator) {

    val previousLaunches = viewModel.previousLaunches.collectAsLazyPagingItems()
    val upcomingLaunches = viewModel.upcomingLaunches.collectAsLazyPagingItems()
    val pagerState = rememberPagerState()
    val navController = rememberNavController()
    val tabs = listOf(
        TabItem.Upcoming(data = upcomingLaunches, navigator = navigator),
        TabItem.Previous(data = previousLaunches, navigator = navigator)
    )
    val uiController = rememberSystemUiController()
    uiController.setStatusBarColor(color = MaterialTheme.colors.statusBarColor)

    Scaffold(topBar = { TopBar() }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}
