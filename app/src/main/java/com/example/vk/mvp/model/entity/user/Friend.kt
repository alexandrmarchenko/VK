package com.example.vk.mvp.model.entity.user

import com.google.gson.annotations.SerializedName

data class Friend (
    @SerializedName("first_name") val firstName : String,
    @SerializedName("id") val id : Int,
    @SerializedName("last_name") val lastName : String,
    @SerializedName("can_access_closed") val canAccessClosed : Boolean,
    @SerializedName("is_closed") val isClosed : Boolean,
    @SerializedName("sex") val sex : Int,
    @SerializedName("photo_50") val photo_50 : String,
    @SerializedName("photo_100") val photo_100 : String,
    @SerializedName("online") val online : Int,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("domain") val domain : String,
    @SerializedName("country") val country : Country,
    @SerializedName("photo_200_orig") val photo_200_orig : String,
    @SerializedName("has_mobile") val hasMobile : Int,
    @SerializedName("can_post") val canPost : Int,
    @SerializedName("can_see_all_posts") val canSeeAllPosts : Int,
    @SerializedName("can_write_private_message") val canWritePrivateMessage : Int,
    @SerializedName("status") val status : String,
    @SerializedName("last_seen") val lastSeen : LastSeen,
    @SerializedName("track_code") val trackCode : String,
    @SerializedName("city") val city: City
)