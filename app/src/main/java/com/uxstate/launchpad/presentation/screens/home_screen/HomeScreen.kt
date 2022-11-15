package com.uxstate.launchpad.presentation.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.uxstate.launchpad.R
import com.uxstate.launchpad.presentation.screens.home_screen.components.LaunchImage
import com.uxstate.launchpad.util.LocalSpacing

@Destination()
@RootNavGraph(start = true)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val launches = viewModel.previousLaunches.collectAsLazyPagingItems()
    val spacing = LocalSpacing.current
    Scaffold(topBar = {
        Text(text = stringResource(R.string.launch_app_label))
    }) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {

            LazyColumn {

                items(items = launches) { launch ->

                    launch?.let {

                        LaunchImage(launch = launch)
                    }
                }
            }
        }
    }
}