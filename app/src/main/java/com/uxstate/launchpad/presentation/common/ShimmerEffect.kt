package com.uxstate.launchpad.presentation.screens.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.uxstate.launchpad.presentation.ui.theme.ShimmerDarkGray
import com.uxstate.launchpad.utils.LocalSpacing

@Composable
fun ShimmerEffect() {
    val spacing = LocalSpacing.current
    LazyColumn(
        contentPadding = PaddingValues(spacing.spaceSmall),
        verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
        content = {
            items(2) {
                AnimatedShimmerItem()
            }
        },
    )
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()

    val alphaValue by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec =
            infiniteRepeatable(
                animation =
                    tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing,
                    ),
                repeatMode = RepeatMode.Reverse,
            ),
        label = "",
    )

    ShimmerPlaceholderItem(alpha = alphaValue)
}

@Composable
fun ShimmerPlaceholderItem(
    alpha: Float,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondary,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(19f / 20f)
                    .padding(spacing.spaceSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            ShimmerBar(
                alpha = alpha,
                height = spacing.spaceLarge,
                modifier = Modifier.fillMaxWidth(.7f),
            )

            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            ShimmerBar(
                alpha = alpha,
                height = spacing.spaceMedium,
                modifier = Modifier.fillMaxWidth(.3f),
            )

            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            ShimmerBar(
                alpha = alpha,
                height = spacing.spaceMedium,
                modifier = Modifier.fillMaxWidth(.4f),
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            Row {
                repeat(5) {
                    ShimmerBar(
                        alpha = alpha,
                        modifier = Modifier.size(spacing.spaceLarge),
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                }
            }
        }
    }
}

@Composable
fun ShimmerBar(
    alpha: Float,
    modifier: Modifier = Modifier,
    height: Dp = 0.dp,
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier =
            modifier
                .height(height)
                .alpha(alpha),
        color = ShimmerDarkGray,
        shadowElevation = spacing.spaceExtraSmall,
    ) {}
}

@Preview
@Composable
fun ShimmerPlaceholderPreview() {
    ShimmerPlaceholderItem(alpha = 1f)
}
