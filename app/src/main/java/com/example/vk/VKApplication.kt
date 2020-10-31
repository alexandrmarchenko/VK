package com.example.vk

import android.app.Application
import com.example.vk.di.AppComponent
import com.example.vk.di.DaggerAppComponent
import com.example.vk.di.friend.FriendSubcomponent
import com.example.vk.di.module.AppModule
import com.example.vk.di.user.UserSubcomponent

class VKApplication : Application() {

    companion object {
        val DEBUG = true
        lateinit var INSTANCE: VKApplication

        fun getAppContext() = INSTANCE
    }

    private lateinit var appComponent: AppComponent

    var userSubcomponent: UserSubcomponent? = null
    var friendSubcomponent: FriendSubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build();
    }

    fun getAppComponent() = appComponent

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun releaseUserSubcomponent() {
        userSubcomponent = null
    }

    fun initFriendSubcomponent() = userSubcomponent?.friendSubcomponent().also {
        friendSubcomponent = it
    }

    fun releaseFriendSubcomponent() {
        friendSubcomponent = null
    }


}