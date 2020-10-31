package com.example.vk.di.module

import androidx.room.Room
import com.example.vk.VKApplication
import com.example.vk.mvp.model.cache.IProfileCache
import com.example.vk.mvp.model.cache.room.RoomProfileCache
import com.example.vk.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(): Database {
        return Room.databaseBuilder(VKApplication.INSTANCE, Database::class.java, Database.DB_NAME)
            .build();
    }

    @Singleton
    @Provides
    fun getProfileCache(db: Database): IProfileCache {
        return RoomProfileCache(db)
    }
}