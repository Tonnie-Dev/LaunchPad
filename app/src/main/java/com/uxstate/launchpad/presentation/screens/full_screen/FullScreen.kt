package com.uxstate.launchpad.presentation.screens.full_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.util.LocalSpacing

@Destination
@Composable
fun FullScreen(launch: Launch) {


    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context)
                    .data(launch.imageUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.placeholder_image)
                    .build()
    )



    Scaffold(topBar = {LaunchTopBar()}) { paddingValues ->
        Image(
                painter = painter, contentDescription = launch.name,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues = paddingValues)
        )
    }


}