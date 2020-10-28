package com.example.vk.ui.profile

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.vk.R
import com.example.vk.responseData.ProfileInfo
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_layout.*
import kotlinx.android.synthetic.main.profile_main_info.*

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = UserModel(getAccessToken())

        val observer = Observer<ProfileInfo?> { serverResponse ->
            fillProfile(serverResponse)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)

        initListeners()
    }

    private fun fillProfile(serverResponse: ProfileInfo?) {
        serverResponse?.response?.let { profileInfo ->
            user_id.text = "id${profileInfo.id}"
            profileFIO.text = "${profileInfo.firstName} ${profileInfo.lastName}"

            var status = profileInfo.status
            if (status.isNullOrBlank()) {
                status = getString(R.string.default_status_text)
            }
            profileStatus.text = status
            profileInfo.userInfo?.let { userInfo ->
                profileLastSeen.text =
                    getString(R.string.last_seen, userInfo.lastSeen?.time.toString())
                userInfo.photo_100?.let { url ->
                    profileImage.load(url) {
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }
    }

    private fun initListeners() {
        profileStatus.setOnClickListener {
            Snackbar.make(
                nav_view,
                "change status clicked",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun getAccessToken(): String? {
        return arguments?.getString("access_token", null)
    }

    companion object {
        fun newInstance(accessToken: String?): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putString("access_token", accessToken)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDrawer()

    }

    private fun initDrawer() {
        val toggle = ActionBarDrawerToggle(requireActivity(), drawer_layout, R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        profile_menu.setOnClickListener {
            drawer_layout.openDrawer(Gravity.RIGHT)
        }
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