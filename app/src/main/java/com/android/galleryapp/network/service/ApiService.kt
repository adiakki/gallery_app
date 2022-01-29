package com.android.galleryapp.network.service

import com.android.galleryapp.domain.model.GalleryDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("get_memes")
    suspend fun getGalleryData(): Response<GalleryDataResponse>
}