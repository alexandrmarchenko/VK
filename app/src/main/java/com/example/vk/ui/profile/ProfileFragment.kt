package com.example.vk.ui.profile

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.vk.R
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_layout.*

class ProfileFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    var activity: Activity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDrawer()

    }

    private fun initDrawer() {
        val toggle =
            ActionBarDrawerToggle(activity, drawer_layout, R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        profile_menu.setOnClickListener {
            drawer_layout.openDrawer(Gravity.RIGHT)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    private val mOnNavigationItemSelectedListener =
        NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_friends -> {
                    Snackbar.make(nav_view, item.title, Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_groups -> {
                    Snackbar.make(nav_view, item.title, Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_liked -> {
                    Snackbar.make(nav_view, item.title, Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_bookmarks -> {
                    Snackbar.make(nav_view, item.title, Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_statistic -> {
                    Snackbar.make(nav_view, item.title, Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_help -> {
                    Snackbar.make(nav_view, item.title, Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_settings -> {
                    Snackbar.make(nav_view, item.title, Snackbar.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }

}