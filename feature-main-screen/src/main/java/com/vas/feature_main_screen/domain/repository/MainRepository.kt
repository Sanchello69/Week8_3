package com.vas.feature_main_screen.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.vas.feature_main_screen.domain.model.CatModel

interface MainRepository {

    fun getCatsResult() : LiveData<PagingData<CatModel>>

    suspend fun postLikeCat(id: String) : String
}