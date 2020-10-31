package com.example.vk.di

import com.example.vk.di.module.*
import com.example.vk.di.user.UserSubcomponent
import com.example.vk.mvp.presenter.MainPresenter
import com.example.vk.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        AuthModule::class,
        CacheModule::class
    )
)
interface AppComponent {
    fun userSubcomponent(): UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}