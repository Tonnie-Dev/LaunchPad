package com.uxstate.launchpad.presentation.screens.common

import androidx.compose.runtime.Composable
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.ui.theme.DahliaYellow
import com.uxstate.launchpad.presentation.ui.theme.LuminousRed
import com.uxstate.launchpad.presentation.ui.theme.MintGreen
import com.uxstate.launchpad.presentation.ui.theme.OysterWhite

@Composable
fun StatusIcon(launch: Launch) {

    val iconTint = when (launch.status) {


        "Success" -> MintGreen
        "Go" -> MintGreen
        "TBC" -> DahliaYellow
        "Failure" -> LuminousRed
        else -> OysterWhite
    }

    

}