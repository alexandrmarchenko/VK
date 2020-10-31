package com.example.vk.mvp.model.repo

import com.example.vk.mvp.model.entity.user.User
import io.reactivex.rxjava3.core.Single

interface IUsersRepo {
    fun getUsers(
        accessToken: String,
        apiVer: Double,
        usersIds: String?,
        fields: String
    ): Single<User>
}