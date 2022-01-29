package com.android.galleryapp.presentation.utils

import android.content.Context
import com.android.galleryapp.R
import com.android.galleryapp.presentation.application.GalleryApplication

class PreferenceHelper {

    private val sharedPref by lazy { GalleryApplication.getAppInstance().getSharedPreferences(
        GalleryApplication.getAppInstance().getString(R.string.pref_file), Context.MODE_PRIVATE) }

    companion object {
        var instance: PreferenceHelper? = null

        fun getPrefInstance() : PreferenceHelper {
            if (instance == null) {
                instance = PreferenceHelper()
            }
            return instance!!
        }
    }

    fun putString(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }

    fun getString(key: String) = sharedPref.getString(key, "")
}