package com.example.vk.mvp.model.entity.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomProfile(
    @PrimaryKey @NonNull
    var accessToken: String,
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val homeTown: String?,
    val status: String?,
    val bdate: String?,
    val bdateVisibility: Int?,
    val city: String?,
    val country: String?,
    val phone: String?,
    val relation: Int?,
    val sex: Int?
)