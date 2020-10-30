package com.example.vk.mvp.model.entity.user

import com.google.gson.annotations.SerializedName

data class FriendList (
    @SerializedName("count") val count : Int,
    @SerializedName("items") val items : List<Friend>
)