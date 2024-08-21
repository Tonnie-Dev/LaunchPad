package com.uxstate.launchpad.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ramcosta.composedestinations.DestinationsNavHost
import com.uxstate.launchpad.presentation.screens.NavGraphs
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LaunchPadTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
