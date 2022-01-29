package com.android.galleryapp.presentation.utils

import android.content.Context
import android.util.DisplayMetrics


object AppUtils {

    fun dpToPx(context: Context, dp: Int): Float {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).toFloat()
    }
}