package com.android.galleryapp.binder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.galleryapp.presentation.utils.loadImage

@BindingAdapter("setImage")
fun ImageView.setImage(url: String) {
    this.loadImage(url)
}