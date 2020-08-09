package com.example.vk.responseData.photo

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id") val id: Int,
    @SerializedName("album_id") val albumId: Int,
    @SerializedName("owner_id") val ownerId: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("text") val text: String,
    @SerializedName("date") val date: Int,
    @SerializedName("sizes") val sizes: ArrayList<PhotoSize>,
    @SerializedName("photo_75") val photo_75: String,
    @SerializedName("photo_130") val photo_130: String,
    @SerializedName("photo_604") val photo_604: String,
    @SerializedName("photo_807") val photo_807: String,
    @SerializedName("photo_1280") val photo_1280: String,
    @SerializedName("photo_2560") val photo_2560: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)