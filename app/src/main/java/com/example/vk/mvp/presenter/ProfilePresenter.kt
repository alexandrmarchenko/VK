package com.example.vk.mvp.presenter

import android.util.Log
import com.example.vk.Constants
import com.example.vk.VKApplication
import com.example.vk.mvp.model.auth.Account
import com.example.vk.mvp.model.entity.profileInfo.ProfileInfo
import com.example.vk.mvp.model.entity.user.User
import com.example.vk.mvp.model.entity.user.UserDetail
import com.example.vk.mvp.model.repo.IProfileRepo
import com.example.vk.mvp.model.repo.IUsersRepo
import com.example.vk.mvp.view.ProfileInfoView
import com.example.vk.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ProfilePresenter() : MvpPresenter<ProfileInfoView>() {
    private val TAG: String = ProfilePresenter::class.java.simpleName

    init {
        VKApplication.INSTANCE.getAppComponent().inject(this)
    }

    @Inject
    lateinit var profileRepo: IProfileRepo

    @Inject
    lateinit var usersRepo: IUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var account: Account

    @Inject
    lateinit var scheduler: Scheduler

    private var profileInfo: ProfileInfo? = null

    private var userInfo: UserDetail? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        account.accessToken?.let {
            profileRepo.getProfileInfo(it, Constants.api_ver)
                .observeOn(scheduler)
                .subscribe({ info: ProfileInfo? ->
                    profileInfo = info
                    info?.response?.let {
                        viewState.setProfileInfo(it)
                        loadDetails()
                    }
                },
                    { e ->
                        Log.w(TAG, "Error" + e.message)
                    })
        }
    }

    private fun loadDetails() {
        val fields =
            "photo_id, verified, sex, bdate, city, country, home_town, has_photo, " +
                    "photo_50, photo_100, photo_200_orig, photo_200, photo_400_orig, " +
                    "photo_max, photo_max_orig, online, domain, has_mobile, contacts, " +
                    "site, education, universities, schools, status, last_seen, followers_count, " +
                    "common_count, occupation, nickname, relatives, relation, personal, " +
                    "connections, exports, activities, interests, music, movies, tv, books, " +
                    "games, about, quotes, can_post, can_see_all_posts, can_see_audio, can_write_private_message, " +
                    "can_send_friend_request, is_favorite, is_hidden_from_feed, timezone, screen_name, " +
                    "maiden_name, crop_photo, is_friend, friend_status, career, military, blacklisted, " +
                    "blacklisted_by_me, can_be_invited_group"
        account.accessToken?.let {
            usersRepo.getUsers(
                it,
                Constants.api_ver,
                null,
                fields
            ).observeOn(scheduler)
                .subscribe({ user: User? ->
                    user?.response?.get(0)?.let { userDetail ->
                        userInfo = userDetail
                        viewState.setUserInfo(userDetail)
                    }
                },
                    { e ->
                        Log.w(TAG, "Error" + e.message)
                    }
                )
        }
    }

    fun openFriendsFragment() {
        userInfo?.id?.let {
            router.navigateTo(Screens.Companion.FriendsFragmentScreen(it))
        }
    }

}