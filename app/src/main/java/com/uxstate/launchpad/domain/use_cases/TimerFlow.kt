package com.uxstate.launchpad.domain.use_cases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach

class TimerFlow {

    // Create the timer flow
    val timer = (0..Int.MAX_VALUE)
            .asSequence()
            .asFlow()
            .onEach { delay(1_000) } // sp
}