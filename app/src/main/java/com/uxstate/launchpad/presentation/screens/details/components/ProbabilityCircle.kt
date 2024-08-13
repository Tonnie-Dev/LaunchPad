package com.uxstate.launchpad.presentation.screens.details.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.LocalSpacing

@Composable
fun ProbabilityCircle(
    probability: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    inactiveColor: Color = Color.LightGray,

    ) {
    val spacing = LocalSpacing.current
    val strokeWidth = (spacing.spaceSmall)

    val animatedProbRatio = remember { Animatable(initialValue = 0f) }
    var probCounter by remember { mutableStateOf(0) }
    val animatedProbInt by animateIntAsState(
        targetValue = probCounter,
        animationSpec = tween(durationMillis = 1_500, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(key1 = probability, block = {

        // when the provided targetValue is changed, the animation will run automatically
        probCounter = probability
        animatedProbRatio.animateTo(
            targetValue = if (probability > 0) (probability / 100f) else 0f,
            animationSpec = tween(durationMillis = 1_500, easing = FastOutSlowInEasing)
        )
    })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center) {

            Canvas(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .aspectRatio(1f)
                    .padding(spacing.spaceSmall),
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
                        sweepAngle = animatedProbRatio.value * 360,
                        useCenter = false,
                        size = size,
                        style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt)
                    )
                }
            )

            Text(
                text = "$animatedProbInt%",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Text(
            text = stringResource(R.string.probability_label),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
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

        )
    }
}