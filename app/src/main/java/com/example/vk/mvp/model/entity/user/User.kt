package com.example.vk.mvp.model.entity.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("response") val response: ArrayList<UserDetail>?
)