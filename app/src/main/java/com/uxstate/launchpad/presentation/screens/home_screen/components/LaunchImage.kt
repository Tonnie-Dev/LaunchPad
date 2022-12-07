package com.uxstate.launchpad.presentation.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.TimerState
import com.uxstate.launchpad.domain.model.computeTimeBoard
import com.uxstate.launchpad.presentation.screens.common.StatusIcon
import com.uxstate.launchpad.presentation.screens.common.TimeBoardWidget
import com.uxstate.launchpad.util.LocalSpacing
import com.uxstate.launchpad.util.formatLaunchDatabaseStringDate
import com.uxstate.launchpad.util.generateSecondsFlow

@Composable
fun LaunchImage(
    launch: Launch,
    showCountDown: Boolean,
    modifier: Modifier = Modifier
) {

    val secondsFlow by launch.generateSecondsFlow()
        .collectAsState(initial = 0)
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = context)
            .data(launch.imageUrl)
            .placeholder(R.drawable.rocket_svgrepo)
            .error(R.drawable.broken_image)
            .crossfade(true)
            .build()

    )

    Box(
        modifier = modifier.padding(spacing.spaceExtraSmall),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painter,
                contentDescription = launch.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = spacing.spaceMedium))
                    .aspectRatio(3f / 2f)

            )

            // Name
            Text(
                text = launch.name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.secondary
            )

            // Agency
            Text(
                text = launch.provider.name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                textAlign = TextAlign.Center
            )

            // Pad

            Text(
                text = launch.pad.locationName,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                textAlign = TextAlign.Center
            )

            if (showCountDown) {

                val timerState = TimerState(secondsFlow)
                val timeBoard = timerState.computeTimeBoard()

                TimeBoardWidget(timeBoard = timeBoard)
            }

            // Date
            Text(
                /* text = formatLaunchDatabaseStringDate(launch.startWindowDate),*/
                text = (launch.startWindowDate).formatLaunchDatabaseStringDate(),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }

        StatusIcon(launch = launch, modifier = Modifier.align(Alignment.TopEnd))
    }
}
