package com.uxstate.launchpad.presentation.screens.details_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun ProbabilityCircle(
    probability: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.Magenta,
    inactiveColor: Color = Color.LightGray,

) {
    val spacing = LocalSpacing.current
    val strokeWidth = (spacing.spaceSmall)

    val animatedProbability = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(key1 = probability, block = {

        animatedProbability.animateTo(
            targetValue = if (probability > 0) (probability / 100f) else 0f,
            animationSpec = tween(durationMillis = 1_500)
        )
    })

    Box(modifier = modifier, contentAlignment = Alignment.Center) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            onDraw = {

                drawArc(
                    color = inactiveColor,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    size = size,
                    style = Stroke(strokeWidth.toPx())
                )

                drawArc(
                    color = activeColor,
                    startAngle = -90f,
                    sweepAngle = animatedProbability.value * 360,
                    useCenter = false,
                    size = size,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt)
                )
            }
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$probability%", style = MaterialTheme.typography.caption)
            Text(text = "Probability", style = MaterialTheme.typography.overline)
        }
    }
}

@Preview
@Composable
fun ProbabilityCirclePreview() {
    val spacing = LocalSpacing.current
    ProbabilityCircle(
        probability = 90,
        modifier = Modifier
            .size(spacing.spaceExtraLarge)
            .padding(spacing.spaceExtraSmall)
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProbabilityCirclePreviewDark() {
    val spacing = LocalSpacing.current
    LaunchPadTheme() {
        ProbabilityCircle(
            probability = 50,
            modifier = Modifier
                .size(spacing.spaceExtraLarge)
                .padding(spacing.spaceExtraSmall)
        )
    }
}