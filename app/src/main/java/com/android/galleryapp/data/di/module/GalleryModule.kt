package com.android.galleryapp.data.di.module

import com.android.galleryapp.data.datasource.GalleryDataSource
import com.android.galleryapp.data.di.scope.GalleryScope
import com.android.galleryapp.data.repository.remote.GalleryRemoteRepositoryImpl
import com.android.galleryapp.data.repository.remote.IGalleryRemoteRepository
import com.android.galleryapp.domain.usecase.GalleryUseCase
import dagger.Module
import dagger.Provides

@Module
class GalleryModule {

    @GalleryScope
    @Provides
    fun provideGalleryRemoteRepository(): IGalleryRemoteRepository = GalleryRemoteRepositoryImpl()

    @GalleryScope
    @Provides
    fun provideGalleryDataSource(remoteRepository: IGalleryRemoteRepository): GalleryDataSource = GalleryDataSource((remoteRepository))


    @GalleryScope
    @Provides
    fun provideGalleryUseCase(galleryDataSource: GalleryDataSource): GalleryUseCase = GalleryUseCase(galleryDataSource)
}