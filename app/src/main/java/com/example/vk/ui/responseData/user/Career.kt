package com.example.vk.ui.responseData.user

import com.google.gson.annotations.SerializedName

data class Career (
    @SerializedName("group_id") val groupId : Int,
    @SerializedName("company") val company : String,
    @SerializedName("country_id") val countryId : Int,
    @SerializedName("city_id") val cityId : Int,
    @SerializedName("city_name") val cityName : String,
    @SerializedName("from") val from : Int,
    @SerializedName("until") val until : Int,
    @SerializedName("position") val position : String
)
