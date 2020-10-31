package com.example.vk.mvp.model.cache.room

import com.example.vk.mvp.model.cache.IProfileCache
import com.example.vk.mvp.model.entity.profileInfo.ProfileInfo
import com.example.vk.mvp.model.entity.profileInfo.ProfileInfoDetails
import com.example.vk.mvp.model.entity.room.Database
import com.example.vk.mvp.model.entity.room.RoomProfile
import com.example.vk.mvp.model.entity.user.City
import com.example.vk.mvp.model.entity.user.Country
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomProfileCache(private val db: Database) : IProfileCache {
    override fun getProfileInfo(accessToken: String): Single<ProfileInfo> {

        return Single.fromCallable<ProfileInfo> {
            val roomProfile: RoomProfile =
                db.profileDao().getProfile(accessToken)
            val profile = ProfileInfo(
                ProfileInfoDetails(
                    roomProfile.firstName,
                    roomProfile.id,
                    roomProfile.lastName,
                    roomProfile.homeTown,
                    roomProfile.status,
                    roomProfile.bdate,
                    roomProfile.bdateVisibility,
                    City(0, roomProfile.city ?: ""),
                    Country(0, roomProfile.country ?: ""),
                    roomProfile.phone,
                    roomProfile.relation,
                    roomProfile.sex
                )
            )
            profile
        }
    }

    override fun insertProfileInfo(
        accessToken: String,
        profileInfo: ProfileInfoDetails
    ): Completable {
        return Completable.fromAction {
            val roomProfile = RoomProfile(
                accessToken,
                profileInfo?.id,
                profileInfo.firstName,
                profileInfo.lastName,
                profileInfo.homeTown,
                profileInfo.status,
                profileInfo.bdate,
                profileInfo.bdateVisibility,
                profileInfo.city?.title,
                profileInfo.country?.title,
                profileInfo.phone,
                profileInfo.relation,
                profileInfo.sex
            )
            db.profileDao().insert(roomProfile)
        }
    }
}