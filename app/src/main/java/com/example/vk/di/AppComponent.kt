package com.example.vk.di

import com.example.vk.di.module.*
import com.example.vk.mvp.presenter.FriendsPresenter
import com.example.vk.mvp.presenter.MainPresenter
import com.example.vk.mvp.presenter.ProfilePresenter
import com.example.vk.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApiModule::class,
        RepoModule::class,
        AppModule::class,
        CiceroneModule::class,
        AuthModule::class,
        CacheModule::class
    )
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(profilePresenter: ProfilePresenter)
    fun inject(friendsPresenter: FriendsPresenter)
}