package com.example.vk.ui.responseData

import com.example.vk.ui.responseData.profile.ProfileInfoDetails
import com.google.gson.annotations.SerializedName

data class ProfileInfo (
    @SerializedName("response") val response: ProfileInfoDetails?
)