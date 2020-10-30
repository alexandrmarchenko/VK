package com.example.vk.mvp.model.entity.user

import com.google.gson.annotations.SerializedName

data class Friends (
    @SerializedName("response") val response : FriendList
)