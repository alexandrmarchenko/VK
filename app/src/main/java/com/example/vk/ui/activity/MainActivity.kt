package com.example.vk.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.vk.R
import com.example.vk.VKApplication
import com.example.vk.mvp.presenter.MainPresenter
import com.example.vk.mvp.view.MainView
import com.example.vk.ui.BackButtonListener
import com.example.vk.mvp.model.auth.Account
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    private val REQUEST_AUTH = 1

    private var navigator: Navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.container)

    @Inject
    lateinit var account: Account

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VKApplication.INSTANCE.getAppComponent().inject(this)

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
                account.accessToken = data?.extras?.getString(AuthActivity.KEY_USER_ID)
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
                    presenter.openNewsFragment()
                    true
                }
                R.id.navigation_messages -> {
                    presenter.openMessagesFragment()
                    true
                }
                R.id.navigation_profile -> {
                    account.accessToken?.let {
                        presenter.openProfileFragment(it)
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is BackButtonListener && (fragment as BackButtonListener).backPressed()) {
                return
            }
        }

        presenter.backClicked()
    }

}