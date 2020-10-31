package com.example.vk.di.module

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneModule {
    private val cicerone = Cicerone.create()

    @Provides
    fun cicerone(): Cicerone<Router> {
        return cicerone
    }

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    @Singleton
    @Provides
    fun router(): Router {
        return cicerone.router
    }
}