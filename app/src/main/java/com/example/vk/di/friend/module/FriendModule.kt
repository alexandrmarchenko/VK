package com.example.vk.di.friend.module

import com.example.vk.di.friend.FriendScope
import com.example.vk.mvp.model.api.IDataSource
import com.example.vk.mvp.model.network.INetworkStatus
import com.example.vk.mvp.model.repo.IFriendsRepo
import com.example.vk.mvp.model.repo.retrofit.RetrofitFriendsRepo
import dagger.Module
import dagger.Provides

@Module
class FriendModule {
    @FriendScope
    @Provides
    fun friendsRepo(api: IDataSource, networkStatus: INetworkStatus): IFriendsRepo =
        RetrofitFriendsRepo(api, networkStatus)
}