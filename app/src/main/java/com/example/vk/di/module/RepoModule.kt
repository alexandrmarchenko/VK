package com.example.vk.di.module

import com.example.vk.mvp.model.api.IDataSource
import com.example.vk.mvp.model.repo.IProfileRepo
import com.example.vk.mvp.model.repo.retrofit.RetrofitProfileRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun profileRepo(api: IDataSource): IProfileRepo = RetrofitProfileRepo(api)
}