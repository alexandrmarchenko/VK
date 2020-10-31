package com.example.vk.mvp.model.repo

import com.example.vk.mvp.model.entity.profileInfo.ProfileInfo
import io.reactivex.rxjava3.core.Single

interface IProfileRepo {
    fun getProfileInfo(accessToken: String, v: Double): Single<ProfileInfo>
}