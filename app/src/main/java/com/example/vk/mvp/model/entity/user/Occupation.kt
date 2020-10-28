package com.example.vk.mvp.model.entity.user

import com.google.gson.annotations.SerializedName

data class Occupation(
    @SerializedName("type ") val type: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name ") val name: String
)