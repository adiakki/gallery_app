package com.android.galleryapp.domain.model

import com.android.galleryapp.domain.entity.GalleryItem
import com.google.gson.annotations.SerializedName

/**
 *{
"success": true,
"data": {
"memes": [
{
"id": "673439",
"name": "Confused Gandalf",
"url": "https://i.imgflip.com/efmn.jpg",
"width": 500,
"height": 607,
"box_count": 3
}
]
}
}
 *
 */

data class GalleryDataResponse(
    @field:SerializedName("success")
    var success: Boolean = false,

    @field:SerializedName("data")
    var galleryData: GalleryData? = null
)

data class ResultData<T>(var data: T?, var dataState: DataState, var message: String? = "", var error: Error? = null)

enum class DataState {LOADING, SUCCESS, ERROR}


data class GalleryData(
    @field:SerializedName("memes")
    var galleryList: ArrayList<GalleryItem>? = null
)