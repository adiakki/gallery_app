package com.android.galleryapp.presentation.application

import android.app.Application
import com.android.galleryapp.data.di.component.DaggerGalleryComponent
import com.android.galleryapp.data.di.component.GalleryComponent
import com.android.galleryapp.data.di.module.GalleryModule
import com.android.galleryapp.data.di.module.NetworkModule

class GalleryApplication : Application() {

    private lateinit  var galleryComponent: GalleryComponent

    companion object {
        lateinit var instance: GalleryApplication

        fun getAppInstance() : GalleryApplication = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun provideGalleryComponent(): GalleryComponent {
        if(!this::galleryComponent.isInitialized) {
            galleryComponent = DaggerGalleryComponent.builder().galleryModule(GalleryModule())
                .networkModule(NetworkModule()).build()
        }
        return galleryComponent
    }
}
