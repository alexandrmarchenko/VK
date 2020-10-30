package com.example.vk.mvp.model.repo.retrofit

import com.example.vk.mvp.model.api.IDataSource
import com.example.vk.mvp.model.entity.user.Friends
import com.example.vk.mvp.model.repo.IFriendsRepo
import io.reactivex.rxjava3.core.Single

class RetrofitFriendsRepo(private val api: IDataSource): IFriendsRepo {
    override fun getFriends(accessToken: String, userId: Int, apiVer: Double, fields: String): Single<Friends> {
        return api.getFriends(accessToken, userId, apiVer, fields)
    }
}