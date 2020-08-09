package com.example.vk.responseData

import com.example.vk.responseData.user.UserDetail
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("response") val response: ArrayList<UserDetail>?
)