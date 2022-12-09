package com.uxstate.launchpad.presentation.screens.splash_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.launchpad.R
import com.uxstate.launchpad.presentation.screens.destinations.HomeScreenDestination

@Destination()
@RootNavGraph(start = true)
@Composable
fun SplashScreen(navigator: DestinationsNavigator) {

    Box(modifier = Modifier.fillMaxHeight()) {

        val spec = LottieCompositionSpec.RawRes(R.raw.rocket_launch_lottie)
        val composition by rememberLottieComposition(spec = spec)
        val state = animateLottieCompositionAsState(composition = composition)

        LottieAnimation(composition = composition, progress = state.progress)

        if (state.isAtEnd && state.isPlaying) {

            navigator.navigate(HomeScreenDestination)
        }
    }
}