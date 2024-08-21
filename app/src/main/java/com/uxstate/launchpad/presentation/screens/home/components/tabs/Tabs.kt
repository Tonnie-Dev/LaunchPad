package com.uxstate.launchpad.presentation.screens.home.components.tabs

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import kotlinx.coroutines.launch

@Composable
fun Tabs(
    tabs: List<TabItem>,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onSurface,
        indicator = { tabPositions ->
            SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = MaterialTheme.colorScheme.secondary,
            )
        },
    ) {
        tabs.forEachIndexed { i, tabItem ->
            // Tab
            LeadingIconTab(
                icon = {
                    Icon(
                        painter = painterResource(id = tabItem.icon),
                        contentDescription = stringResource(R.string.upcoming_label),
                    )
                },
                text = { Text(text = tabItem.title) },
                selected = pagerState.currentPage == i,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(i) } },
            )
        }
    }
}

@Preview
@Composable
fun TabsPreviewLight() {
       /* val tabs = listOf(TabItem.Previous(data = null ), TabItem.Upcoming)
        val pagerState = rememberPagerState{tabs.size}
        LaunchPadTheme() {
            Tabs(tabs = tabs, pagerState = pagerState)
    }*/
}
