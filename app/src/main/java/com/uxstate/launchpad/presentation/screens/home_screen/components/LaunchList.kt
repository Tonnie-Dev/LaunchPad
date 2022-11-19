package com.uxstate.launchpad.presentation.screens.home_screen.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.uxstate.launchpad.domain.model.Launch

@Composable
fun LaunchList(data: LazyPagingItems<Launch>?, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier, content = {

        items(data!!) { launch ->

            launch?.let {

                LaunchImage(launch = launch)
            }
        }
    })
}