package com.example.vk.responseData.user

import com.google.gson.annotations.SerializedName

data class Universities (
    @SerializedName("id") val id : Int,
    @SerializedName("country ") val country : Int,
    @SerializedName("city") val city : Int,
    @SerializedName("name") val name : String,
    @SerializedName("faculty") val faculty : Int,
    @SerializedName("faculty_name") val facultyName : String,
    @SerializedName("chair") val chair : Int,
    @SerializedName("chair_name") val chairName : String,
    @SerializedName("graduation") val graduation : Int,
    @SerializedName("education_form") val educationForm : String,
    @SerializedName("education_status") val educationStatus : String
)