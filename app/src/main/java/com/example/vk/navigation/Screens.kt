package com.example.vk.navigation

import androidx.fragment.app.Fragment
import com.example.vk.ui.fragment.FriendsFragment
import com.example.vk.ui.fragment.MessagesFragment
import com.example.vk.ui.fragment.NewsFragment
import com.example.vk.ui.fragment.ProfileFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    companion object{
        class ProfileFragmentScreen(): SupportAppScreen() {
            override fun getFragment(): Fragment? {
                return ProfileFragment.newInstance()
            }
        }


        class NewsFragmentScreen: SupportAppScreen() {
            override fun getFragment(): Fragment? {
                return NewsFragment.newInstance()
            }
        }

        class MessagesFragmentScreen: SupportAppScreen() {
            override fun getFragment(): Fragment? {
                return MessagesFragment.newInstance()
            }
        }

        class FriendsFragmentScreen(private val userId: Int): SupportAppScreen() {
            override fun getFragment(): Fragment? {
                return FriendsFragment.newInstance(userId)
            }
        }
    }
}