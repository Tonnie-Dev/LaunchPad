package com.uxstate.launchpad.domain.useCases

import androidx.paging.PagingData
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.repository.LaunchRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class GetPreviousLaunchesUseCase @Inject constructor(
    private val repository: LaunchRepository
) {

    operator fun invoke(): Flow<PagingData<Launch>> {

        return repository.getPreviousLaunches()
    }
}
