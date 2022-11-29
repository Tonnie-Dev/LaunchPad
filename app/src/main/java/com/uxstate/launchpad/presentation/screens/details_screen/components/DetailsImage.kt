package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
    val rocketIcons = listOf(
            RocketIconDataClass(
                    itemText = stringResource(R.string.rocket_label),
                    value = launch.rocket.name,
                    icon = R.drawable.rocket_icon
            ),
            RocketIconDataClass(
                    itemText = launch.rocket.family,
                    value = stringResource(R.string.family_label),
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

        LazyRow {

            items(rocketIcons) { icon ->
                RocketIcon(itemText = icon.itemText, value = icon.value, icon = icon.icon)
            }
        }

    }


}

data class RocketIconDataClass(val itemText: String, val value: String, @DrawableRes val icon: Int)

