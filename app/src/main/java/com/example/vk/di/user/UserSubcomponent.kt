package com.example.vk.di.user

import com.example.vk.di.friend.FriendSubcomponent
import com.example.vk.di.user.module.UserModule
import com.example.vk.mvp.presenter.ProfilePresenter
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserSubcomponent {
    fun friendSubcomponent(): FriendSubcomponent

    fun inject(profilePresenter: ProfilePresenter)
}