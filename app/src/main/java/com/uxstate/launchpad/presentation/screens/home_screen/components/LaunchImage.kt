package com.uxstate.launchpad.presentation.screens.home_screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.uxstate.launchpad.util.LocalSpacing
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LaunchImage(
    launch: Launch,
    showCountDown: Boolean,
    state: TimerState,
    modifier: Modifier = Modifier
) {
    val job: Job? = null

    val flow = makeFlow(launch).collectAsState(initial = 0)
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    var remainingTime by remember {
        mutableStateOf(0L)
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = context)
            .data(launch.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.broken_image)
            .crossfade(true)
            .build()

    )
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painter,
            contentDescription = launch.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
                .padding(spacing.spaceSmall)
        )

        // Name
        Text(
            text = launch.name,
            style = MaterialTheme.typography.h5,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            textAlign = TextAlign.Center
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

            val timerState = TimerState(flow.value)
            val timeBoard = timerState.computeTimeBoard()

            val days = timeBoard.days
            val hours = timeBoard.hours
            val minutes = timeBoard.minutes
            val seconds = timeBoard.seconds

            TimeBoardWidget(timeBoard = timeBoard)
            /*Text(
                // text = TimerState(launch = launch).toString(),
                text = "$seconds",
                style = MaterialTheme.typography.h5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                textAlign = TextAlign.Center
            )*/
        }

        // Date
        Text(
            text = formatStringDate(launch.startWindowDate),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatStringDate(date: String): String {
    // convert string date to local date
    val temporalAccessor: TemporalAccessor = DateTimeFormatter.ISO_INSTANT.parse(date)
    val instant: Instant = Instant.from(temporalAccessor)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())

    val pattern = "dd-MM-yyyy HH:mm a"
    val dateFormatter = DateTimeFormatter.ofPattern(pattern)

    return localDateTime.format(dateFormatter)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun readStringDateToMillis(launch: Launch): Long {

    // convert string date to local date
    val temporalAccessor: TemporalAccessor =
        DateTimeFormatter.ISO_INSTANT.parse(launch.startWindowDate)
    val instant: Instant = Instant.from(temporalAccessor)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())

    // convert local date to millis

    return localDateTime.atZone(ZoneId.systemDefault())
        .toEpochSecond()
}

@RequiresApi(Build.VERSION_CODES.O)
private fun makeFlow(launch: Launch): Flow<Long> = flow {
    val countDownFrom = readStringDateToMillis(launch)
    var counter = countDownFrom
    emit(counter)

    while (counter > 0) {
        delay(1.seconds)

        counter--
        emit(counter)
    }
}
