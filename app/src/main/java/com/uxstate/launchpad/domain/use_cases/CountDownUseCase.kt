package com.uxstate.launchpad.domain.use_cases

import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.O)
class CountDownUseCase {

    operator fun invoke(launchDate: LocalDateTime): Flow<String> =
        flow {
            var remainingTimeString: String = ""
            val currentDateTime = System.currentTimeMillis()

            // convert LocalDateTime to millis
            val zdt = launchDate.atZone(ZoneId.systemDefault())
            val futureLaunchDate = zdt.toInstant()
                .toEpochMilli()
            val timeDifference = futureLaunchDate - currentDateTime

            val countDownTimer = object : CountDownTimer(timeDifference, 1000) {
                override fun onTick(millscUntilFinish: Long) {
                    remainingTimeString = """
                   
                   ${TimeUnit.MILLISECONDS.toDays(millscUntilFinish)}:
                   ${TimeUnit.MILLISECONDS.toHours(millscUntilFinish) % 24}: 
                   ${TimeUnit.MILLISECONDS.toMinutes(millscUntilFinish) % 60}:
                   ${TimeUnit.MILLISECONDS.toSeconds(millscUntilFinish) % 60}
                    """.trimIndent()
                }

                override fun onFinish() {
                    Timber.i("Time is Up!")
                }
            }

            countDownTimer.start()
        }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun setTimer(launchDate: LocalDateTime) {
    var remainingTimeString: String? = null
    val currentDateTime = System.currentTimeMillis()

    // convert LocalDateTime to millis
    val zdt = launchDate.atZone(ZoneId.systemDefault())
    val futureLaunchDate = zdt.toInstant()
        .toEpochMilli()
    val timeDifference = futureLaunchDate - currentDateTime

    val countDownTimer = object : CountDownTimer(timeDifference, 1000) {
        override fun onTick(millscUntilFinish: Long) {

            remainingTimeString = """
                    
                   ${TimeUnit.MILLISECONDS.toDays(millscUntilFinish)}:
                   ${TimeUnit.MILLISECONDS.toHours(millscUntilFinish) % 24}: 
                   ${TimeUnit.MILLISECONDS.toMinutes(millscUntilFinish) % 60}:
                   ${TimeUnit.MILLISECONDS.toSeconds(millscUntilFinish) % 60}
            """.trimIndent()
        }

        override fun onFinish() {
            Timber.i("Time is Up!")
        }
    }

    countDownTimer.start()
}
