package com.android.galleryapp.data.repository.remote

import com.android.galleryapp.network.service.ApiService
import com.android.galleryapp.presentation.application.GalleryApplication
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GalleryRemoteRepositoryImpl : IGalleryRemoteRepository {

    @Inject
    lateinit var apiService: ApiService

    init {
        GalleryApplication.getAppInstance().provideGalleryComponent().inject(this)
    }

    override suspend fun fetchGalleryList() = flow {
        emit(apiService.getGalleryData())
    }
}