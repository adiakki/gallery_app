package com.android.galleryapp.domain.usecase

import com.android.galleryapp.domain.model.GalleryDataResponse
import com.android.galleryapp.domain.model.ResultData
import kotlinx.coroutines.flow.Flow

interface IGalleryUseCase {
    suspend fun fetchGalleryList() : Flow<ResultData<out GalleryDataResponse>>
}