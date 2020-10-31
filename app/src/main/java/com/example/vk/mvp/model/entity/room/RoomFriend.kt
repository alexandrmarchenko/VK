package com.example.vk.mvp.model.entity.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomFriend(
    @PrimaryKey @NonNull
    val id: Int
)