package com.example.vk.di.module

import com.example.vk.mvp.model.api.IDataSource
import com.example.vk.mvp.model.cache.IProfileCache
import com.example.vk.mvp.model.network.INetworkStatus
import com.example.vk.mvp.model.repo.IFriendsRepo
import com.example.vk.mvp.model.repo.IProfileRepo
import com.example.vk.mvp.model.repo.IUsersRepo
import com.example.vk.mvp.model.repo.retrofit.RetrofitFriendsRepo
import com.example.vk.mvp.model.repo.retrofit.RetrofitProfileRepo
import com.example.vk.mvp.model.repo.retrofit.RetrofitUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun profileRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IProfileCache
    ): IProfileRepo = RetrofitProfileRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun friendsRepo(api: IDataSource, networkStatus: INetworkStatus): IFriendsRepo =
        RetrofitFriendsRepo(api, networkStatus)

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus): IUsersRepo =
        RetrofitUsersRepo(api, networkStatus)
}