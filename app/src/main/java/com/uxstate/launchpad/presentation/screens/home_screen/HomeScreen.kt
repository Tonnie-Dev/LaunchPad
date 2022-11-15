package com.uxstate.launchpad.presentation.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.uxstate.launchpad.R

@Destination()
@RootNavGraph(start = true)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val launches = viewModel.previousLaunches.collectAsLazyPagingItems()

    Scaffold(topBar = { Text(text = stringResource(R.string.launch_app_label)) }) { paddingValues ->

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
    ) {
        
    }

    }


}