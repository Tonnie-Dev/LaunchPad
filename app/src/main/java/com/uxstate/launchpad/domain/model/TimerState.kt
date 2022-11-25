package com.uxstate.launchpad.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
data class TimerState(
    val totalSecondsToLaunch: Long = 0

) {

    private val currentDateTime = System.currentTimeMillis()

    private val timeDifference = totalSecondsToLaunch - currentDateTime / 1000
    val daysLeft = TimeUnit.SECONDS.toDays(timeDifference)
    val hoursLeft = TimeUnit.SECONDS.toHours(timeDifference) % 24
    val minutesLeft = TimeUnit.SECONDS.toMinutes(timeDifference) % 60
    val secondsLeft = TimeUnit.SECONDS.toSeconds(timeDifference) % 60

    val textWhenStopped: String = "- - -"
    /* val displaySeconds: String =
         (secondsRemaining ?: textWhenStopped).toString()*/

    // Always implement toString from Effective Java Item 9
    override fun toString(): String {
        return String.format("%02d:%02d:%02d", hoursLeft, minutesLeft, secondsLeft)
        // return "$daysLeft: $hoursLeft : $minutesLeft $secondsLeft"
    }
}
@RequiresApi(Build.VERSION_CODES.O)
fun TimerState.computeTimeBoard(): TimeBoard {
    return TimeBoard(
        days = if (this.daysLeft >= 0) this.daysLeft.toInt() else 0,
        hours = if (this.daysLeft >= 0) this.daysLeft.toInt() else 0,
        minutes = if (this.daysLeft >= 0) this.daysLeft.toInt() else 0,
        seconds = if (this.secondsLeft >= 0) this.secondsLeft.toInt() else 0,
    )
}
data class TimeBoard(val days: Int, val hours: Int, val minutes: Int, val seconds: Int)
