package com.uxstate.launchpad.domain.use_cases

import androidx.paging.PagingData
import androidx.paging.map
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUpcomingLaunchesUseCase @Inject constructor(
    private val repository: LaunchRepository
) {

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
