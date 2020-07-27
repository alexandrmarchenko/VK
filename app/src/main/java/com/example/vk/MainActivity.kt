package com.example.vk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vk.ui.messages.MessagesFragment
import com.example.vk.ui.news.NewsFragment
import com.example.vk.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        nav_view.selectedItemId = R.id.navigation_news
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_news -> {
                    val newsFragment = NewsFragment.newInstance()
                    openFragment(newsFragment)
                    true
                }
                R.id.navigation_messages -> {
                    val messagesFragment = MessagesFragment.newInstance()
                    openFragment(messagesFragment)
                    true
                }
                R.id.navigation_profile -> {
                    val profileFragment = ProfileFragment.newInstance()
                    openFragment(profileFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}