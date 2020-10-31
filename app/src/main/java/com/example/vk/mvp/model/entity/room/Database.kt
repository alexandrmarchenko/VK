package com.example.vk.mvp.model.entity.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vk.VKApplication
import com.example.vk.mvp.model.entity.room.dao.FriendDao
import com.example.vk.mvp.model.entity.room.dao.ProfileDao
import com.example.vk.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [RoomFriend::class, RoomProfile::class, RoomUser::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun profileDao(): ProfileDao
    abstract fun friendDao(): FriendDao

    companion object {
        val DB_NAME = "database.db"
        @Volatile
        lateinit var INSTANCE: Database

        fun getInstance(): Database {
            var localRef = INSTANCE
            if (localRef == null) {
                synchronized(Database::class) {
                    INSTANCE = localRef

                    if (localRef == null) {
                        localRef = Room.databaseBuilder(
                            VKApplication.getAppContext(),
                            Database::class.java,
                            DB_NAME
                        ).build()

                        INSTANCE = localRef
                    }
                }
            }
            return localRef
        }
    }

}