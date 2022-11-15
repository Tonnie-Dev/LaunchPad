package com.uxstate.launchpad.presentation.screens.home_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun LaunchImage() {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(model = ImageRequest.Builder())
}