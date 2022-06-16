package com.vas.feature_main_screen.domain.useCase

import com.vas.feature_main_screen.domain.repository.MainRepository

class PostLikeUseCase(private val mainRepository: MainRepository) {

    suspend fun execute(id: String): String {
        return mainRepository.postLikeCat(id)
    }
}