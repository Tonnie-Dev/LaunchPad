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
import com.uxstate.launchpad.util.LocalSpacing
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.concurrent.TimeUnit
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LaunchImage(
    launch: Launch,
    showCountDown: Boolean,
    state: TimerState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    var remainingTime by remember {
        mutableStateOf(TimerState().toString())
    }

    LaunchedEffect(key1 = launch, block = {

        remainingTime = state.toString()

        Timber.i("Inside LI LaunchedEffect time is: $remainingTime")
    })

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = context)
            .data(launch.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.broken_image)
            .crossfade(true)
            .build()

    )
    Column {
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

            // Timber.i("Test Timer ${TimerState(launch = launch)}")
            // T-Time
            Text(
                // text = TimerState(launch = launch).toString(),
                text = remainingTime,
                style = MaterialTheme.typography.h5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                textAlign = TextAlign.Center
            )
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
/*
@RequiresApi(Build.VERSION_CODES.O)
fun countDownTimer(launch: Launch) {
    var time = "ss"

    val currentDateTime = System.currentTimeMillis()

    // convert LocalDateTime to millis
    val zdt = launch.startWindowDate.atZone(ZoneId.systemDefault())
    val futureLaunchDate = zdt.toInstant()
            .toEpochMilli()
    val timeDifference = futureLaunchDate - currentDateTime

    val countDownTimer = object : CountDownTimer(timeDifference, 1000) {
        override fun onTick(millSecUntilFinish: Long) {
            time = parseCountDown(millSecUntilFinish)

            Timber.i(
                    "OnTicK- $millSecUntilFinish T= ${parseCountDown(millSecUntilFinish)}"
            )
        }

        override fun onFinish() {
            Timber.i("Time is Up!")
        }
    }

    countDownTimer.start()
}
*/

fun parseCountDown(millSecUntilFinish: Long = 0): String {

    return """
                   
  ${TimeUnit.MILLISECONDS.toDays(millSecUntilFinish)}:${
    TimeUnit.MILLISECONDS.toHours(
        millSecUntilFinish
    ) % 24
    }: ${TimeUnit.MILLISECONDS.toMinutes(millSecUntilFinish) % 60}:${
    TimeUnit.MILLISECONDS.toSeconds(
        millSecUntilFinish
    ) % 60
    }
    """.trimIndent()
}
