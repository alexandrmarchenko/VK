package com.example.vk.mvp.model.repo.retrofit

import com.example.vk.mvp.model.api.IDataSource
import com.example.vk.mvp.model.entity.profileInfo.ProfileInfo
import com.example.vk.mvp.model.entity.user.User
import com.example.vk.mvp.model.repo.IProfileRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitProfileRepo(private val api: IDataSource) : IProfileRepo {

    override fun getProfileInfo(accessToken: String, apiVer: Double): Single<ProfileInfo> {
        return api.getProfileInfo(accessToken, apiVer).subscribeOn(Schedulers.io())
    }

    override fun getProfileInfoDetails(
        accessToken: String,
        apiVer: Double,
        usersIds: String,
        fields: String
    ): Single<User> {
        return api.getUsers(accessToken, apiVer, usersIds, fields)
    }


}