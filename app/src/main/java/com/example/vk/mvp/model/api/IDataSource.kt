package com.example.vk.mvp.model.api

import com.example.vk.mvp.model.entity.profileInfo.ProfileInfo
import com.example.vk.mvp.model.entity.user.Friends
import com.example.vk.mvp.model.entity.user.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {
    @GET("/method/account.getProfileInfo")
    fun getProfileInfo(
        @Query("access_token") accessToken: String,
        @Query("v") v: Double
    ): Single<ProfileInfo>

    @GET("/method/users.get")
    fun getUsers(
        @Query("access_token") accessToken: String,
        @Query("v") v: Double,
        @Query("users_ids") users_ids: String,
        @Query("fields") fields: String
    ): Single<User>

    @GET("/method/friends.get")
    fun getFriends(
        @Query("access_token") accessToken: String,
        @Query("user_id") userId: Int,
        @Query("v") v: Double,
        @Query("fields") fields: String
    ): Single<Friends>
}