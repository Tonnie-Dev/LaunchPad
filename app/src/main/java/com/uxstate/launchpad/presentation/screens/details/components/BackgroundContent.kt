package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.screens.common.TimeBoardWidget
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.LocalSpacing
import com.uxstate.launchpad.utils.generateLaunch

@Composable
fun BackgroundContent(
    launch: Launch,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current


    val painter = if (launch.imageUrl.startsWith("android.resource://")) {
        painterResource(id = R.drawable.falcon_9)
    } else {
        rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                        .data(launch.imageUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.placeholder_image)
                        .build()

        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        Image(
                painter = painter,
                contentDescription = launch.name,
                modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(19f / 20f),
                contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(spacing.spaceSmall))

        RocketInfoRow(launch = launch)

        TimeBoardWidget(launch = launch)

    }

}


@PreviewLightDark
@Composable
private fun BackgroundContentPreview() {

    LaunchPadTheme {

        Surface {
            BackgroundContent(
                    launch = generateLaunch()
            )
        }
    }
}