package com.example.vk.mvp.presenter

import com.example.vk.VKApplication
import com.example.vk.mvp.view.MainView
import com.example.vk.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter: MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    init {
        VKApplication.INSTANCE.getAppComponent().inject(this)
    }

    fun openNewsFragment() {
        router.navigateTo(Screens.Companion.NewsFragmentScreen())
    }

    fun openMessagesFragment() {
        router.navigateTo(Screens.Companion.MessagesFragmentScreen())
    }

    fun openProfileFragment(accessToken: String) {
        router.navigateTo(Screens.Companion.ProfileFragmentScreen())
    }

    fun backClicked() {
        router.exit()
    }
}