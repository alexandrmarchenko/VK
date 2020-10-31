package com.example.vk.mvp.model.repo.retrofit

import com.example.vk.mvp.model.api.IDataSource
import com.example.vk.mvp.model.cache.IProfileCache
import com.example.vk.mvp.model.entity.profileInfo.ProfileInfo
import com.example.vk.mvp.model.network.INetworkStatus
import com.example.vk.mvp.model.repo.IProfileRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitProfileRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IProfileCache
) : IProfileRepo {

    override fun getProfileInfo(accessToken: String, apiVer: Double): Single<ProfileInfo> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                return@flatMap api.getProfileInfo(accessToken, apiVer)
                    .flatMap { profile ->
                        cache.insertProfileInfo(accessToken, profile.response!!)
                            .toSingleDefault(profile)
                    }
            } else {
                return@flatMap cache.getProfileInfo(accessToken)
            }
        }.subscribeOn(Schedulers.io())
    }
}