package com.example.vk.ui.responseData

import com.example.vk.ui.responseData.user.UserDetail
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("response") val response: ArrayList<UserDetail>?
)