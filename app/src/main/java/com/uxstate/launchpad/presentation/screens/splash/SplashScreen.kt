package com.uxstate.launchpad.presentation.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.uxstate.launchpad.utils.LocalSpacing

@Destination()
@RootNavGraph(start = true)
@Composable
fun SplashScreen(navigator: DestinationsNavigator) {
    val spacing = LocalSpacing.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {

        val spec = LottieCompositionSpec.RawRes(R.raw.rocket_launch_lottie)
        val composition by rememberLottieComposition(spec = spec)
        val state = animateLottieCompositionAsState(composition = composition)

        LottieAnimation(
            composition = composition, progress = state.progress,
            modifier = Modifier.size(spacing.spaceFiveHundred, spacing.spaceFiveHundred * 5)
        )

        if (state.isAtEnd && state.isPlaying) {

            navigator.navigate(HomeScreenDestination)
        }
    }
}
