package com.example.vk.responseData.profile

import com.example.vk.responseData.user.City
import com.example.vk.responseData.user.Country
import com.example.vk.responseData.user.UserDetail
import com.google.gson.annotations.SerializedName

data class ProfileInfoDetails (
    @SerializedName("first_name") val firstName : String?,
    @SerializedName("id") val id : Int?,
    @SerializedName("last_name") val lastName : String?,
    @SerializedName("home_town") val homeTown : String?,
    @SerializedName("status") val status : String?,
    @SerializedName("bdate") val bdate : String?,
    @SerializedName("bdate_visibility") val bdateVisibility : Int?,
    @SerializedName("city") val city : City?,
    @SerializedName("country") val country : Country?,
    @SerializedName("phone") val phone : String?,
    @SerializedName("relation") val relation : Int?,
    @SerializedName("sex") val sex : Int?,
    var userInfo : UserDetail?
)