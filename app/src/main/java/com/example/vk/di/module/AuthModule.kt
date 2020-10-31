package com.example.vk.di.module

import com.example.vk.mvp.model.auth.Account
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {

    @Singleton
    @Provides
    fun account() = Account()

}