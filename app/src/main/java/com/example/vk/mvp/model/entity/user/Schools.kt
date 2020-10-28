package com.example.vk.mvp.model.entity.user

import com.google.gson.annotations.SerializedName

data class Schools (
    @SerializedName("id") val id : Int,
    @SerializedName("country ") val country : Int,
    @SerializedName("city") val city : Int,
    @SerializedName("name") val name : String,
    @SerializedName("year_from") val yearFrom : Int,
    @SerializedName("year_to") val yearTo : Int,
    @SerializedName("year_graduated") val yearGraduated : Int,
    @SerializedName("class") val clas : String,
    @SerializedName("speciality") val speciality : Int,
    @SerializedName("type") val type : Int,
    @SerializedName("type_str") val typeStr : String

)