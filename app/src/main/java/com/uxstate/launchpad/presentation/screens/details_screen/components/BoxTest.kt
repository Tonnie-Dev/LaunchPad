package com.uxstate.launchpad.presentation.screens.details_screen.components

import android.widget.Switch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TestScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.background(Color.Yellow)) {
        val switchState = remember { mutableStateOf(true) }
        Switch(
            checked = true,
            enabled = true,
            onCheckedChange = { }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SwitchPreview() {
    TestScreen()
}