package com.example.vk.di.module

import com.example.vk.VKApplication
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(private val app: VKApplication) {

    @Provides
    fun app(): VKApplication = app

    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}