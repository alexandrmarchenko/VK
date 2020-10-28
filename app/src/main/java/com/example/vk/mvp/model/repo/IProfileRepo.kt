package com.example.vk.mvp.model.repo

import com.example.vk.mvp.model.entity.profileInfo.ProfileInfo
import com.example.vk.mvp.model.entity.user.User
import io.reactivex.rxjava3.core.Single

interface IProfileRepo {
    fun getProfileInfo(accessToken: String, v: Double): Single<ProfileInfo>
    fun getProfileInfoDetails(
        accessToken: String,
        apiVer: Double,
        usersIds: String,
        fields: String
    ): Single<User>
}