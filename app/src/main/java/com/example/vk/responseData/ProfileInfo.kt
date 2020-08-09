package com.example.vk.responseData

import com.example.vk.responseData.profile.ProfileInfoDetails
import com.google.gson.annotations.SerializedName

data class ProfileInfo (
    @SerializedName("response") val response: ProfileInfoDetails?
)