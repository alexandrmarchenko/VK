package com.example.vk.di.friend

import com.example.vk.di.friend.module.FriendModule
import com.example.vk.mvp.presenter.FriendsPresenter
import dagger.Subcomponent

@FriendScope
@Subcomponent(modules = [FriendModule::class])
interface FriendSubcomponent {
    fun inject(friendsPresenter: FriendsPresenter)
}