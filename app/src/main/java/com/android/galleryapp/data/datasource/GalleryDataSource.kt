package com.android.galleryapp.data.datasource

import com.android.galleryapp.data.repository.remote.IGalleryRemoteRepository
import javax.inject.Inject

class GalleryDataSource @Inject constructor (private val remoteRepository: IGalleryRemoteRepository) :
    IGalleryDataSource {
    override suspend fun fetchGalleryList() = remoteRepository.fetchGalleryList()
}