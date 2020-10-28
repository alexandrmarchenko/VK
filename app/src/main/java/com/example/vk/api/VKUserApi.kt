package com.example.vk.api

import com.example.vk.responseData.ProfileInfo
import com.example.vk.responseData.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VKUserApi {
    @GET("/method/account.getProfileInfo")
    fun getProfileInfo(
        @Query("access_token") accessToken: String?,
        @Query("v") v: Double
    ): Call<ProfileInfo>

    @GET("/method/users.get")
    fun getUsers(
        @Query("access_token") accessToken: String?,
        @Query("v") v: Double,
        @Query("users_ids") users_ids: String,
        @Query("fields") fields: String
    ): Call<User>

}