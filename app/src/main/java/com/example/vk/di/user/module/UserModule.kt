package com.example.vk.di.user.module

import com.example.vk.di.user.UserScope
import com.example.vk.mvp.model.api.IDataSource
import com.example.vk.mvp.model.cache.IProfileCache
import com.example.vk.mvp.model.cache.room.RoomProfileCache
import com.example.vk.mvp.model.entity.room.Database
import com.example.vk.mvp.model.network.INetworkStatus
import com.example.vk.mvp.model.repo.IProfileRepo
import com.example.vk.mvp.model.repo.IUsersRepo
import com.example.vk.mvp.model.repo.retrofit.RetrofitProfileRepo
import com.example.vk.mvp.model.repo.retrofit.RetrofitUsersRepo
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun getProfileCache(db: Database): IProfileCache {
        return RoomProfileCache(db)
    }

    @UserScope
    @Provides
    fun profileRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IProfileCache
    ): IProfileRepo = RetrofitProfileRepo(api, networkStatus, cache)

    @UserScope
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus): IUsersRepo =
        RetrofitUsersRepo(api, networkStatus)
}