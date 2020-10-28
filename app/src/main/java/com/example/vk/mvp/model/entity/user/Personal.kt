package com.example.vk.mvp.model.entity.user

import com.google.gson.annotations.SerializedName

data class Personal(
    @SerializedName("political") val political: Int,
    @SerializedName("langs") val langs: ArrayList<String>,
    @SerializedName("religion") val religion: String,
    @SerializedName("inspired_by") val inspiredBy: String,
    @SerializedName("people_main") val peopleMain: Int,
    @SerializedName("life_main") val lifeMain: Int,
    @SerializedName("smoking") val smoking: Int,
    @SerializedName("alcohol") val alcohol: Int
)
