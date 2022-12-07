package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun BackgroundContent(
    launch: Launch,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onShowFullScreen: () -> Unit,
    imageFractionHeight: Float = 0f,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context)
                    .data(launch.imageUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.placeholder_image)
                    .build()

    )
    val rocketIcons = listOf(
            RocketIconDataClass(
                    itemText = stringResource(R.string.rocket_label),
                    value = launch.rocket.name,
                    icon = R.drawable.rocket_icon
            ),
            RocketIconDataClass(
                    itemText = stringResource(R.string.family_label),
                    value = launch.rocket.family,
                    icon = R.drawable.flight_icon
            ),
            RocketIconDataClass(
                    itemText = stringResource(R.string.agency_label),
                    value = launch.provider.name,
                    icon = R.drawable.flag_icon
            ),
            RocketIconDataClass(
                    itemText = stringResource(R.string.type_label),
                    value = launch.provider.type,
                    icon = R.drawable.satellite_icon
            )
    )
    Column(
            modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(imageFractionHeight + .085f)
    ) {

        Box(
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
                    Text(text = "Full Screen", style = MaterialTheme.typography.body1)
                    Icon(
                            painter = painterResource(id = R.drawable.fullscreen_icon),
                            contentDescription = "Close",
                            tint = MaterialTheme.colors.secondary,
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
                            style = MaterialTheme.typography.body1
                    )
                    Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.close_text),
                            tint = MaterialTheme.colors.secondary,
                            modifier = Modifier
                                    .size(spacing.spaceExtraLarge)

                    )
                }
            }

            Image(
                    painter = painter,
                    contentDescription = launch.name,
                    modifier = Modifier
                            .fillMaxWidth(imageFractionHeight + MIN_BACKGROUND_IMAGE_HEIGHT)
                            .aspectRatio(19f / 20f),
                    contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        LazyRow {

            items(rocketIcons) { icon ->
                RocketIcon(itemText = icon.itemText, value = icon.value, icon = icon.icon)
            }
        }
    }
}

data class RocketIconDataClass(val itemText: String, val value: String, @DrawableRes val icon: Int)
