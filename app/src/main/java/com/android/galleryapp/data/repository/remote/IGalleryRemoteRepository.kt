package com.android.galleryapp.data.repository.remote

import com.android.galleryapp.domain.model.GalleryDataResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IGalleryRemoteRepository {
    suspend fun fetchGalleryList() : Flow<Response<GalleryDataResponse>>
}