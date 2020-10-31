package com.example.vk.mvp.model.entity.room.dao

import androidx.room.*
import com.example.vk.mvp.model.entity.room.RoomFriend

@Dao
interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomFriend)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomFriend>)

    @Update
    fun update(user: RoomFriend)

    @Update
    fun update(users: List<RoomFriend>)

    @Delete
    fun delete(user: RoomFriend)

    @Delete
    fun delete(users: List<RoomFriend>)
}