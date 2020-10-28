package com.example.vk.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.vk.R
import com.example.vk.mvp.presenter.MainPresenter
import com.example.vk.mvp.view.MainView
import com.example.vk.ui.auth.Account
import com.example.vk.ui.auth.AuthActivity
import com.example.vk.ui.fragment.MessagesFragment
import com.example.vk.ui.fragment.NewsFragment
import com.example.vk.ui.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private val REQUEST_AUTH = 1

    val account = Account()

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

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