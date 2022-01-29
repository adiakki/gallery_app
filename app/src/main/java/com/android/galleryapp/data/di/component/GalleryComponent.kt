package com.android.galleryapp.data.di.component

import com.android.galleryapp.data.di.module.GalleryModule
import com.android.galleryapp.data.di.module.NetworkModule
import com.android.galleryapp.data.di.scope.GalleryScope
import com.android.galleryapp.data.repository.remote.GalleryRemoteRepositoryImpl
import com.android.galleryapp.network.service.ApiService
import com.android.galleryapp.presentation.factory.GalleryViewModelFactory
import dagger.Component

@GalleryScope
@Component (modules = [NetworkModule::class, GalleryModule::class])
interface GalleryComponent {
    fun inject(holdingViewModelFactory: GalleryViewModelFactory)
    fun inject(holdingRemoteRepositoryImpl: GalleryRemoteRepositoryImpl)
    fun provideApiService(): ApiService
}