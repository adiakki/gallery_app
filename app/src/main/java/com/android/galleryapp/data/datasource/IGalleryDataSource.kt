package com.android.galleryapp.data.datasource

import com.android.galleryapp.domain.model.GalleryDataResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IGalleryDataSource {
    suspend fun fetchGalleryList() : Flow<Response<GalleryDataResponse>>
}