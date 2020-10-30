package com.example.vk.mvp.presenter

import android.util.Log
import com.example.vk.Constants
import com.example.vk.VKApplication
import com.example.vk.mvp.model.entity.user.Friend
import com.example.vk.mvp.model.repo.IFriendsRepo
import com.example.vk.mvp.presenter.list.IFriendListPresenter
import com.example.vk.mvp.view.FriendsView
import com.example.vk.mvp.view.IFriendItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class FriendsPresenter(private val accessToken: String?, private val userId: Int) : MvpPresenter<FriendsView>() {
    private val TAG: String = FriendsPresenter::class.java.simpleName

    private val VERBOSE = true

    init {
        VKApplication.INSTANCE.getAppComponent().inject(this)
    }

    @Inject
    lateinit var friendsRepo: IFriendsRepo

    @Inject
    lateinit var scheduler: Scheduler

//    private var profileInfo: ProfileInfo? = null

    inner class FriendListPresenter : IFriendListPresenter {
        internal var friends = ArrayList<Friend>()
        override fun onItemClick(view: IFriendItemView) {
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos())
            }
        }

        override fun bindView(view: IFriendItemView) {
            val friend = friends[view.getPos()]
            view.init(friend)
        }

        override fun getCount(): Int {
            return friends.size
        }
    }

    private val friendListPresenter = FriendListPresenter()

    fun getPresenter() = friendListPresenter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

    }

    private fun loadData() {

        val fields =
            "nickname, domain, sex, bdate, city, country, timezone, photo_50, photo_100, photo_200_orig, has_mobile, contacts, education, online, relation, last_seen, status, can_write_private_message, can_see_all_posts, can_post, universities"
        accessToken?.let {
            friendsRepo.getFriends(accessToken, userId, Constants.api_ver, fields)
                .observeOn(scheduler)
                .subscribe({ friendsList ->
                    friendListPresenter.friends.clear()
                    friendListPresenter.friends.addAll(friendsList.response.items)
                    viewState.updateList()

                }, { e -> Log.w(TAG, "Error" + e.message) })
        }
    }
}