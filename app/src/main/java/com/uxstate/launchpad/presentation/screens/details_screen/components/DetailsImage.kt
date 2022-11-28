package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun DetailsImage(launch: Launch, modifier: Modifier = Modifier) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(launch.imageUrl)
            .crossfade(true)
            .placeholder(R.drawable.placeholder_image)
            .build()

    )

    Column(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painter,
            contentDescription = launch.name,
            modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(9f / 10f),
            contentScale = ContentScale.Crop
        )

        // name
        Text(
            text = launch.name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth()) {

        }
    }
}