package com.example.vk.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vk.R
import com.example.vk.mvp.model.entity.user.UserDetail
import com.example.vk.mvp.presenter.FriendsPresenter
import com.example.vk.mvp.view.FriendsView
import com.example.vk.ui.adapter.FriendRVAdapter
import kotlinx.android.synthetic.main.fragment_friends.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FriendsFragment(private val userId: Int) : MvpAppCompatFragment(), FriendsView {

    lateinit var friendRVAdapter: FriendRVAdapter

    @InjectPresenter
    lateinit var presenter: FriendsPresenter

    @ProvidePresenter
    fun provideFriendsPresenter(): FriendsPresenter {
        val userId = arguments?.getInt(FriendsFragment.ARG_PARAM1, 0) ?: 0
        val accessToken = arguments?.getString(FriendsFragment.ARG_ACCESS_TOKEN, null)
        return FriendsPresenter(accessToken, userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun init() {
        friendRVAdapter = FriendRVAdapter(presenter.getPresenter())
        val layoutManager = LinearLayoutManager(context)

        rv_friends.layoutManager = layoutManager
        rv_friends.adapter = friendRVAdapter
    }

    override fun updateList() {
        friendRVAdapter.notifyDataSetChanged()
    }

    companion object {
        private val ARG_PARAM1 = "user_id";
        private val ARG_ACCESS_TOKEN = "access_token";
        fun newInstance(accessToken: String, userId: Int): FriendsFragment {
            val fragment = FriendsFragment(userId)
            val args = Bundle()
            args.putInt(ARG_PARAM1, userId)
            args.putString(ARG_ACCESS_TOKEN, accessToken)
            fragment.arguments = args
            return fragment
        }
    }


}