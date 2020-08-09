package com.example.vk.ui.responseData.photo

import com.google.gson.annotations.SerializedName

data class CropPhoto(
    @SerializedName("photo") val photo: Photo,
    @SerializedName("crop") val crop: Crop
)