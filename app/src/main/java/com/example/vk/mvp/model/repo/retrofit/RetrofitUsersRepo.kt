package com.example.vk.mvp.model.repo.retrofit

import android.accounts.NetworkErrorException
import com.example.vk.mvp.model.api.IDataSource
import com.example.vk.mvp.model.entity.user.User
import com.example.vk.mvp.model.network.INetworkStatus
import com.example.vk.mvp.model.repo.IUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus
) : IUsersRepo {

    override fun getUsers(
        accessToken: String,
        apiVer: Double,
        usersIds: String?,
        fields: String
    ): Single<User> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                return@flatMap api.getUsers(accessToken, apiVer, usersIds, fields)
            } else {
                throw NetworkErrorException("Отсутсвует подключение к сети")
            }
        }.subscribeOn(Schedulers.io())
    }
}