package com.android.galleryapp.domain.entity

import com.google.gson.annotations.SerializedName

/***
 *{
"id": "673439",
"name": "Confused Gandalf",
"url": "https://i.imgflip.com/efmn.jpg",
"width": 500,
"height": 607,
"box_count": 3
}
 *
 *
 */
data class GalleryItem(
    @field:SerializedName("id")
    var id: Long = 0,
    @field:SerializedName("name")
    var name: String? = "",
    @field:SerializedName("url")
    var url: String? = "",
)
