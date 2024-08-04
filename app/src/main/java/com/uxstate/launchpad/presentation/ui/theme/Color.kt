package com.uxstate.launchpad.presentation.ui.theme


import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance


// Defaults
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// Primary
val Black900 = Color(0xFF000000)
val Black400 = Color(0xFF2C2C2E)

// Secondary
val Pink300 = Color(0xFFF700BD)
val Pink800 = Color(0xFFBF008C)

val ShimmerDarkGray = Color(0xFF1D1D1D)

// Status Icon Tint Colors
val LuminousRed = Color(0xFFFE0000)
val DahliaYellow = Color(0xFFF3A505)
val OysterWhite = Color(0xFFEAE6CA)
val MintGreen = Color(0xFF06F773)


val ColorScheme.isLight:Boolean
    get() = this.primary.luminance() > 0.5


// StatusBar extension color from MaterialTheme3
val ColorScheme.statusBarColor
    get() = if (this.isLight) Black900 else Black400




