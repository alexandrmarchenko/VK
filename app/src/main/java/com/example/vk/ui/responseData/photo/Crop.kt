package com.example.vk.ui.responseData.photo

import com.google.gson.annotations.SerializedName

data class Crop(
    @SerializedName("x") val x: Double,
    @SerializedName("y") val y: Double,
    @SerializedName("x2") val x2: Double,
    @SerializedName("y2") val y2: Double
)