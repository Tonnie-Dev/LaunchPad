package com.uxstate.launchpad.presentation.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun LaunchImage(launch: Launch, modifier: Modifier = Modifier) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = context)
                    .data(launch.imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.broken_image)
                    .crossfade(true)
                    .build()

    )

    Image(
            painter = painter,
            contentDescription = launch.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
    )
}