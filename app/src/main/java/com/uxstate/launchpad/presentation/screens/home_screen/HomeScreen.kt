package com.uxstate.launchpad.presentation.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.uxstate.launchpad.presentation.screens.home_screen.components.LaunchImage
import com.uxstate.launchpad.util.LocalSpacing

@Destination()
@RootNavGraph(start = true)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val previousLaunches = viewModel.previousLaunches.collectAsLazyPagingItems()
    val upcomingLaunches = viewModel.upcomingLaunches.collectAsLazyPagingItems()
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {

        LazyColumn {

            items(items = previousLaunches) { launch ->

                launch?.let {

                    LaunchImage(launch = launch)
                }
            }
        }
    }
}
