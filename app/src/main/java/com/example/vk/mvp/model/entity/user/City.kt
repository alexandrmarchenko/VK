package com.example.vk.mvp.model.entity.user

import com.google.gson.annotations.SerializedName

data class City (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String
)
