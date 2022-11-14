package com.uxstate.launchpad.domain.use_cases

import androidx.paging.PagingData
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow

class GetPreviousLaunchesUseCase (private val repository: LaunchRepository) {

    operator fun  invoke():Flow<PagingData<Launch>>{

        return repository.getPreviousLaunches()
    }
}