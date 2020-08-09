package com.example.vk.ui.responseData.photo

import com.google.gson.annotations.SerializedName

data class PhotoSize(
    @SerializedName("url ") val url: String,
    @SerializedName("width ") val width: Int,
    @SerializedName("height ") val height: Int,
    @SerializedName("type ") val type: String
)