package com.uxstate.launchpad.presentation.screens.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.uxstate.launchpad.utils.LocalSpacing
import com.uxstate.launchpad.utils.generateLaunch

@Composable
fun BackgroundContent(
    launch: Launch,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onShowFullScreen: () -> Unit,
    imageFractionHeight: Float = 1f,
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

    Column(
            modifier = modifier
                    .fillMaxSize()

    ) {
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
        LazyRow {

            /*items(rocketIcons) { icon ->
                RocketIcon(itemText = icon.itemText, value = icon.value, icon = icon.icon)
            }*/
        }
        /* Box(
                 modifier = Modifier
                         .fillMaxWidth()
                         .fillMaxHeight(
                                 .6f
                         )
         ) {

             Row(
                     modifier = Modifier.align(alignment = Alignment.BottomEnd),
                     verticalAlignment = Alignment.CenterVertically
             ) {
                 Row(
                         modifier = Modifier.clickable { onShowFullScreen() },
                         verticalAlignment = Alignment.CenterVertically
                 ) {
                     Text(text = "Full Screen", style = MaterialTheme.typography.bodyLarge)
                     Icon(
                             painter = painterResource(id = R.drawable.fullscreen_icon),
                             contentDescription = "Close",
                             tint = MaterialTheme.colorScheme.secondary,
                             modifier = Modifier
                                     .size(spacing.spaceExtraLarge)

                     )
                 }
                 Row(
                         modifier = Modifier.clickable { onClose() },
                         verticalAlignment = Alignment.CenterVertically
                 ) {
                     Text(
                             text = stringResource(R.string.close_text),
                             style = MaterialTheme.typography.bodyLarge
                     )
                     Icon(
                             imageVector = Icons.Default.Close,
                             contentDescription = stringResource(R.string.close_text),
                             tint = MaterialTheme.colorScheme.secondary,
                             modifier = Modifier
                                     .size(spacing.spaceExtraLarge)

                     )
                 }
             }


        }*/


    }
}





@PreviewLightDark
@Composable
private fun BackgroundContentPreview() {


    LaunchPadTheme {

        Surface {
            BackgroundContent(
                    launch = generateLaunch(),
                    onClose = {},
                    onShowFullScreen = {}
            )
        }
    }
}