package com.example.vk.ui.responseData.user

import com.google.gson.annotations.SerializedName

data class LastSeen(
    @SerializedName("time ") val time: Int,
    @SerializedName("platform ") val platform: Int
)