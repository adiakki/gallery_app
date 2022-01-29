package com.android.galleryapp.domain.usecase

import com.android.galleryapp.data.datasource.GalleryDataSource
import com.android.galleryapp.domain.model.DataState
import com.android.galleryapp.domain.model.ResultData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GalleryUseCase @Inject constructor(private val dataSource: GalleryDataSource) : IGalleryUseCase {

    override suspend fun fetchGalleryList() = flow {
        dataSource.fetchGalleryList().collect { response->
            if (response.isSuccessful) {
                emit(ResultData(response.body(), DataState.SUCCESS))
            }
            else {
                emit(ResultData(null, DataState.ERROR, response.message(), null))
            }
        }
    }
}