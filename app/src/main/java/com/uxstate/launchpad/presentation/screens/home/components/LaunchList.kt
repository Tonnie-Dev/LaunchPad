package com.uxstate.launchpad.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.common.EmptyScreen
import com.uxstate.launchpad.presentation.screens.common.ShimmerEffect
import com.uxstate.launchpad.presentation.screens.destinations.DetailsScreenDestination

@Composable
fun LaunchList(
    data: LazyPagingItems<Launch>,
    showCountDown: Boolean,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,

    ) {

    val result = handlePagingResult(launches = data)

    if (result) {
        LazyColumn(modifier = modifier, content = {

            items(
                    count = data.itemCount,
                    key = data.itemKey { it.id },
                    contentType = data.itemContentType{"Launch"}
            ) { i ->

                val launch = data[i]
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
}

// starts with small letter because this function returns a value
@Composable
fun handlePagingResult(launches: LazyPagingItems<Launch>): Boolean {

    // apply to get access to loadState
    launches.apply {
        // this variable stores errors found in refresh, prepend or append
        val error = when {

            loadState.refresh is LoadState.Error -> {
                loadState.refresh as LoadState.Error
            }

            loadState.prepend is LoadState.Error -> {
                loadState.prepend as LoadState.Error
            }

            loadState.append is LoadState.Error -> {
                loadState.append as LoadState.Error
            }

            // else suggesting the error is null
            else -> null
        }

        return when {

            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }

            // in case of an error
            error != null -> {
                EmptyScreen(error = error, launches = launches)
                false
            }

            // less than one is when we don't receive any heroes
            launches.itemCount < 1 -> {

                // empty screen meaning default icon and message
                EmptyScreen()
                false
            }
            // if there is no error and data is not loading any more we show lazy column as above
            else -> true
        }
    }
}
