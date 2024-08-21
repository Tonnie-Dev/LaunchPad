package com.uxstate.launchpad.presentation.screens.home.components.tabs

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TabsContent(
    tabs: List<TabItem>,
    pagerState: PagerState,
) {
    HorizontalPager(state = pagerState, pageSize = PageSize.Fill) {
            page ->

        tabs[page].composeFunction()
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun TabsContentPreviewLight() {
   /* val tabs = listOf(TabItem.Upcoming, TabItem.Previous)
    val pagerState = rememberPagerState()
    LaunchPadTheme() {
        TabsContent(tabs = tabs, pagerState = pagerState)
    }*/
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TabsContentPreviewDark() {
   /* val tabs = listOf(TabItem.Upcoming, TabItem.Previous)
    val pagerState = rememberPagerState()
    LaunchPadTheme() {
        TabsContent(tabs = tabs, pagerState = pagerState)
    }*/
}
