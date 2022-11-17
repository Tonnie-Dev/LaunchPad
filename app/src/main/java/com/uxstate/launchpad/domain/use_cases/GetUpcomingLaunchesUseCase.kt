package com.uxstate.launchpad.domain.use_cases

import com.uxstate.launchpad.domain.repository.LaunchRepository

class GetUpcomingLaunchesUseCase(private val repository: LaunchRepository) {
    operator fun invoke() {
        repository.getUpcomingLaunches()
    }
}