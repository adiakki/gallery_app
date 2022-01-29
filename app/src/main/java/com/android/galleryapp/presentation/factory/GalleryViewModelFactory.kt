package com.android.galleryapp.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.galleryapp.domain.usecase.GalleryUseCase
import com.android.galleryapp.presentation.application.GalleryApplication
import com.android.galleryapp.presentation.viewmodel.GalleryViewModel
import javax.inject.Inject

class GalleryViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var galleryUseCase: GalleryUseCase

    init {
        GalleryApplication.getAppInstance().provideGalleryComponent().inject(this)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) return GalleryViewModel(galleryUseCase) as T
            throw IllegalArgumentException("Unknown class name")
    }
}