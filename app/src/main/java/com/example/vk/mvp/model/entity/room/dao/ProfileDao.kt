package com.example.vk.mvp.model.entity.room.dao

import androidx.room.*
import com.example.vk.mvp.model.entity.room.RoomProfile

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomProfile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomProfile>)

    @Update
    fun update(user: RoomProfile)

    @Update
    fun update(users: List<RoomProfile>)

    @Delete
    fun delete(user: RoomProfile)

    @Delete
    fun delete(users: List<RoomProfile>)

    @Query("SELECT * FROM RoomProfile Where accessToken = :token limit 1")
    fun getProfile(token: String): RoomProfile
}