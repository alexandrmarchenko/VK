package com.example.vk.di.module

import com.example.vk.VKApplication
import com.example.vk.mvp.model.network.INetworkStatus
import com.example.vk.ui.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
class AppModule(private val app: VKApplication) {

    @Provides
    fun app(): VKApplication = app

    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    fun networkStatus(): INetworkStatus = AndroidNetworkStatus()
}