package com.uxstate.launchpad.domain.use_cases

import androidx.paging.PagingData
import androidx.paging.map
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.repository.LaunchRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUpcomingLaunchesUseCase(private val repository: LaunchRepository) {

    operator fun invoke(): Flow<PagingData<Launch>> {
        return repository.getUpcomingLaunches()
            .map { pagingData ->
                pagingData.map {
                    it
                    // it.copy(startWindowDate = parseStringDate(it.startWindowDate))
                }
            }
    }

    private fun parseStringDate(date: String): LocalDateTime {

        val pattern = "dd-MM-yy hh:mm aa"
        val formatter = DateTimeFormatter.ofPattern(pattern)

        return LocalDateTime.parse(date, formatter)
    }
}
