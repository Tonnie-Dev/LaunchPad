package com.uxstate.launchpad.domain.model

data class TimerState(
    val secondsRemaining: Int? = null,
    val totalSeconds: Int = 60,
    val textWhenStopped: String = "-"
) {
    val displaySeconds: String =
        (secondsRemaining ?: textWhenStopped).toString()

    // Show 100% if seconds remaining is null
    val progressPercentage: Float =
        (secondsRemaining ?: totalSeconds) / totalSeconds.toFloat()

    // Always implement toString from Effective Java Item 9
    override fun toString(): String = "Seconds Remaining $secondsRemaining, totalSeconds: $totalSeconds, progress: $progressPercentage"

}
