package com.example.vk

import android.app.Application
import com.example.vk.di.AppComponent
import com.example.vk.di.DaggerAppComponent
import com.example.vk.di.module.AppModule

class VKApplication : Application() {

    companion object {
        val DEBUG = true
        lateinit var INSTANCE: VKApplication
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build();
    }

    fun getAppComponent() = appComponent


}