package com.example.vk.di

import com.example.vk.di.module.ApiModule
import com.example.vk.di.module.AppModule
import com.example.vk.di.module.RepoModule
import com.example.vk.mvp.presenter.ProfilePresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApiModule::class,
        RepoModule::class,
        AppModule::class
    )
)
interface AppComponent {
    fun inject(profilePresenter: ProfilePresenter)
}