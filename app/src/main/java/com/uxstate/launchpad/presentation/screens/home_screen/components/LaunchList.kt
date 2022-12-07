package com.uxstate.launchpad.presentation.screens.home_screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.destinations.DetailsScreenDestination

@Composable
fun LaunchList(
    data: LazyPagingItems<Launch>,
    showCountDown: Boolean,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
) {

    LazyColumn(modifier = modifier, content = {

        items(data) { launch ->

            launch?.let {

                LaunchImage(
                    launch = it,
                    showCountDown = showCountDown,
                    modifier = Modifier.clickable {
                        navigator.navigate(
                            DetailsScreenDestination(it)
                        )
                    }

                )
            }
        }
    })
}