package com.example.vk.ui.fragment

import android.accounts.Account
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentTransaction
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.vk.R
import com.example.vk.mvp.model.entity.profileInfo.ProfileInfoDetails
import com.example.vk.mvp.model.entity.user.UserDetail
import com.example.vk.mvp.presenter.ProfilePresenter
import com.example.vk.mvp.view.ProfileInfoView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_layout.*
import kotlinx.android.synthetic.main.profile_main_info.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ProfileFragment(private val accessToken: String?) : MvpAppCompatFragment(),
    ProfileInfoView {

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

    @InjectPresenter
    lateinit var mPresenter: ProfilePresenter

    @ProvidePresenter
    fun provideProfilePresenter(): ProfilePresenter {

        val accessToken = arguments?.getString(ARG_PARAM1, null)
        return ProfilePresenter(accessToken)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initListeners()
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
        return arguments?.getString(ARG_PARAM1, null)
    }

    companion object {
        private val ARG_PARAM1 = "access_token";
        fun newInstance(accessToken: String?): ProfileFragment {
            val fragment = ProfileFragment(accessToken)
            val args = Bundle()
            args.putString(ARG_PARAM1, accessToken)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDrawer()

    }

    private fun initDrawer() {
        val toggle = ActionBarDrawerToggle(
            requireActivity(),
            drawer_layout,
            R.string.app_name,
            R.string.app_name
        )
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
//                    Snackbar.make(nav_view, item.title, Snackbar.LENGTH_SHORT).show()

                    mPresenter.openFriendsFragment()

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

    override fun setProfileInfo(profileInfo: ProfileInfoDetails) {

        profileFIO.text = "${profileInfo.firstName} ${profileInfo.lastName}"

        var status = profileInfo.status
        if (status.isNullOrBlank()) {
            status = getString(R.string.default_status_text)
        }
        profileStatus.text = status
    }

    override fun setUserInfo(userDetail: UserDetail) {
        user_id.text = userDetail.screenName
        profileLastSeen.text =
            getString(R.string.last_seen, userDetail.lastSeen?.time.toString())
        userDetail.photo_100?.let { url ->
            profileImage.load(url) {
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun openFriendsFragment(userId: Int) {
        accessToken?.let {
            val friendsFragment =
                FriendsFragment.newInstance(accessToken, userId)
            var ft = fragmentManager?.beginTransaction()
            ft?.replace(R.id.container, friendsFragment)
            ft?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft?.commit()
        }
    }

}