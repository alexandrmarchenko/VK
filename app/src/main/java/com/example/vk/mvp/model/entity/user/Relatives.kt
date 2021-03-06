package com.example.vk.mvp.model.entity.user

import com.google.gson.annotations.SerializedName

data class Relatives(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String
)
