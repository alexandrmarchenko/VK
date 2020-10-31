package com.example.vk.mvp.model.cache

import com.example.vk.mvp.model.entity.profileInfo.ProfileInfo
import com.example.vk.mvp.model.entity.profileInfo.ProfileInfoDetails
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IProfileCache {
    fun getProfileInfo(accessToken: String): Single<ProfileInfo>
    fun insertProfileInfo(accessToken: String, profileInfo: ProfileInfoDetails): Completable
}