package com.example.vk.mvp.model.repo

import com.example.vk.mvp.model.entity.user.Friends
import io.reactivex.rxjava3.core.Single

interface IFriendsRepo {
    fun getFriends(accessToken: String, userId: Int, apiVer: Double, fields: String): Single<Friends>
}