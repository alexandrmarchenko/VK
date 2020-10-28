package com.example.vk

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vk.ui.messages.MessagesFragment
import com.example.vk.ui.news.NewsFragment
import com.example.vk.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_AUTH = 1

    val account = Account()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        account.restore(this)
        if (account.accessToken.isNullOrBlank()) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivityForResult(intent, REQUEST_AUTH)
        }

        initBottomNav()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_AUTH) {
            if (resultCode == Activity.RESULT_OK) {
                account.accessToken = data?.extras?.getString("user_id")
                account.save(this)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun initBottomNav() {
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
                    val profileFragment = ProfileFragment.newInstance(account.accessToken)
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