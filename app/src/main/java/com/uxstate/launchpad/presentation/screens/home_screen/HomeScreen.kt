package com.uxstate.launchpad.presentation.screens.home_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination()
@RootNavGraph(start = true)
@Composable
fun HomeScreen() {

    Text(text = "The Richest Man in Babylon")
}