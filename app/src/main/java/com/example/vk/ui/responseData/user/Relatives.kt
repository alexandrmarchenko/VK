package com.example.vk.ui.responseData.user

import com.google.gson.annotations.SerializedName

data class Relatives(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String
)
