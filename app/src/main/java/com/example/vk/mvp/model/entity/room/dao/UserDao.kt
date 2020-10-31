package com.example.vk.mvp.model.entity.room.dao

import androidx.room.*
import com.example.vk.mvp.model.entity.room.RoomUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomUser>)

    @Update
    fun update(user: RoomUser)

    @Update
    fun update(users: List<RoomUser>)

    @Delete
    fun delete(user: RoomUser)

    @Delete
    fun delete(users: List<RoomUser>)

    @Query("SELECT * FROM RoomUser Where id in (:id) ")
    fun getUsers(id: List<String>): List<RoomUser>
}