package com.uxstate.launchpad.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.uxstate.launchpad.utils.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> PullToRefreshLazyColumn(
    items: List<T>,
    keyExtractor: (T) -> Any,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    content: @Composable (T) -> Unit
) {

    val spacing = LocalSpacing.current
    val pullToRefreshState = rememberPullToRefreshState()

    Box(modifier = modifier.nestedScroll(connection = pullToRefreshState.nestedScrollConnection)) {

        LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(
                        spacing.spaceSmall
                )
        ) {

            items(items = items, key = keyExtractor) {

                content(it)
            }

        }


        //Listen to Refreshing State [ pullToRefreshState ]

        if (pullToRefreshState.isRefreshing) {

            LaunchedEffect(key1 = true) {

                onRefresh()

            }
        }

        //Listen to the External isRefreshing state

        LaunchedEffect(key1 = isRefreshing) {

            if (isRefreshing) {
                pullToRefreshState.startRefresh()
            } else {
                pullToRefreshState.endRefresh()
            }
        }


        PullToRefreshContainer(state = pullToRefreshState, modifier = Modifier.align(Alignment.TopCenter))

//PullToRefreshBox

    }

}