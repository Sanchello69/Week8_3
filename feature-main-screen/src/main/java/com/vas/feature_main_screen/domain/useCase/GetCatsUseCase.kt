package com.vas.feature_main_screen.domain.useCase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.vas.feature_main_screen.domain.model.CatModel
import com.vas.feature_main_screen.domain.repository.MainRepository

class GetCatsUseCase(private val mainRepository: MainRepository) {

    fun execute(): LiveData<PagingData<CatModel>> {
        return mainRepository.getCatsResult()
    }
}