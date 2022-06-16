package com.vas.feature_main_screen.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.vas.feature_main_screen.data.network.Api
import com.vas.feature_main_screen.data.pagingSources.CatListPagingSource
import com.vas.feature_main_screen.data.pagingSources.NETWORK_PAGE_SIZE
import com.vas.feature_main_screen.domain.model.CatModel
import com.vas.feature_main_screen.domain.repository.MainRepository

class MainRepositoryImpl(private val api: Api): MainRepository {

    override fun getCatsResult() : LiveData<PagingData<CatModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CatListPagingSource(api = api)
            }
        ).liveData
    }

    override suspend fun postLikeCat(id: String) : String {
        return api.postLike(id)
    }
}