package com.example.vk.mvp.model.repo.retrofit

import android.accounts.NetworkErrorException
import com.example.vk.mvp.model.api.IDataSource
import com.example.vk.mvp.model.entity.user.Friends
import com.example.vk.mvp.model.network.INetworkStatus
import com.example.vk.mvp.model.repo.IFriendsRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitFriendsRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus
) : IFriendsRepo {
    override fun getFriends(
        accessToken: String,
        userId: Int,
        apiVer: Double,
        fields: String
    ): Single<Friends> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                return@flatMap api.getFriends(accessToken, userId, apiVer, fields)
            } else {
                throw NetworkErrorException("Отсутсвует подключение к сети")
            }
        }.subscribeOn(Schedulers.io())

    }
}