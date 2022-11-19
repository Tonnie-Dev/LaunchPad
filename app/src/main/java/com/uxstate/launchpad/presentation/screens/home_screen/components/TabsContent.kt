package com.uxstate.launchpad.presentation.screens.home_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {

    HorizontalPager(state = pagerState, count = tabs.size) {
        page ->

        tabs[page].screen()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun TabsContentPreviewLight() {

    /*val tabs = listOf(TabItem.Upcoming, TabItem.Previous)
    val pagerState = rememberPagerState()
    LaunchPadTheme() {
        TabsContent(tabs = tabs, pagerState = pagerState)
    }*/
}

@OptIn(ExperimentalPagerApi::class)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TabsContentPreviewDark() {

   /* val tabs = listOf(TabItem.Upcoming, TabItem.Previous)
    val pagerState = rememberPagerState()
    LaunchPadTheme() {
        TabsContent(tabs = tabs, pagerState = pagerState)
    }*/
}