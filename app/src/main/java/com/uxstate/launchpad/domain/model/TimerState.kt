package com.uxstate.launchpad.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
data class TimerState(
    val totalSecondsToLaunch: Long = 0

) {

    private val currentDateTime = System.currentTimeMillis()

    // convert LocalDateTime to millis

    /*private val zdt: ZonedDateTime = launch.startWindowDate.atZone(ZoneId.systemDefault())
    private val futureLaunchDate = zdt.toInstant()
        .toEpochMilli()*/
    private val timeDifference = totalSecondsToLaunch - currentDateTime / 1000

    private val daysLeft = TimeUnit.SECONDS.toDays(timeDifference)
    private val hoursLeft = TimeUnit.SECONDS.toHours(timeDifference) % 24
    private val minutesLeft = TimeUnit.SECONDS.toMinutes(timeDifference) % 60
    private val secondsLeft = TimeUnit.SECONDS.toSeconds(timeDifference) % 60

    val textWhenStopped: String = "- - -"
    /* val displaySeconds: String =
         (secondsRemaining ?: textWhenStopped).toString()*/

    // Always implement toString from Effective Java Item 9
    override fun toString(): String {
        return String.format("%02d:%02d:%02d", hoursLeft, minutesLeft, secondsLeft)
        // return "$daysLeft: $hoursLeft : $minutesLeft $secondsLeft"
    }
}
