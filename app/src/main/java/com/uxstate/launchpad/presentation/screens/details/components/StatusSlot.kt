package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.ui.theme.DahliaYellow
import com.uxstate.launchpad.presentation.ui.theme.LuminousRed
import com.uxstate.launchpad.presentation.ui.theme.MintGreen
import com.uxstate.launchpad.presentation.ui.theme.OysterWhite
import com.uxstate.launchpad.utils.LocalSpacing

@Composable
fun StatusSlot(launch: Launch) {

    val backgroundColor = when (launch.status.abbrev) {

        "Success" -> MintGreen
        "Go" -> MintGreen
        "TBD" -> DahliaYellow
        "Failure" -> LuminousRed
        else -> OysterWhite
    }

    val transition = rememberInfiniteTransition(label = "")

    val alphaValue by transition.animateFloat(
        initialValue = 1f,
        targetValue = .3f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3_000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val spacing = LocalSpacing.current
    Spacer(modifier = Modifier.height(spacing.spaceMedium + spacing.spaceSmall))
    Text(
        text = (launch.status.name),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(CutCornerShape(spacing.spaceExtraSmall))
            .background(color = backgroundColor.copy(alpha = alphaValue))
    )
}