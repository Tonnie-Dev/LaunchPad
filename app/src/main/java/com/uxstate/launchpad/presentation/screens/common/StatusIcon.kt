package com.uxstate.launchpad.presentation.screens.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.*
import com.uxstate.launchpad.presentation.ui.theme.DahliaYellow
import com.uxstate.launchpad.presentation.ui.theme.LuminousRed
import com.uxstate.launchpad.presentation.ui.theme.MintGreen
import com.uxstate.launchpad.presentation.ui.theme.OysterWhite
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun StatusIcon(launch: Launch, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current

    @DrawableRes
    val icon = when (launch.status.abbrev) {

        "Success" -> R.drawable.check_icon
        "Go" -> R.drawable.outbound_icon
        "TBD" -> R.drawable.pending_icon
        "Failure" -> R.drawable.dangerous_icon
        else -> R.drawable.lens_icon
    }
    val iconTint = when (launch.status.abbrev) {

        "Success" -> MintGreen
        "Go" -> MintGreen
        "TBD" -> DahliaYellow
        "Failure" -> LuminousRed
        else -> OysterWhite
    }

    val iconDesc = when (launch.status.abbrev) {

        "Success" -> stringResource(R.string.success_label)
        "Go" -> stringResource(R.string.go_label)
        "TBD" -> stringResource(R.string.tbd_label)
        "Failure" -> stringResource(R.string.failure_label)
        else -> stringResource(R.string.null_label)
    }

    Icon(
        painter = painterResource(icon),
        tint = iconTint,
        contentDescription = iconDesc,
        modifier = modifier.size(spacing.spaceLarge)
    )
}

@Preview
@Composable
fun StatusIconPreview() {

    val launch = Launch(
        id = 0,
        name = "",
        mission = Mission(
            name = "",
            description = "",
            type = ""
        ),
        imageUrl = "",
        provider = Provider(id = 0, name = "", type = ""),
        status = Status(name = "Name", abbrev = "TBD", description = ""),
        pad = Pad(
            locationName = "",
            latitude = "",
            longitude = "",
            complex = "",
            totalLaunchCount = 0,
            totalLandingCount = 0,

        ),
        startWindowDate = "",
        rocket = Rocket(name = "", family = "")
    )

    StatusIcon(launch = launch)
}