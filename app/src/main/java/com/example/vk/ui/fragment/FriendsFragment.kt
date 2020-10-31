package com.example.vk.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vk.R
import com.example.vk.VKApplication
import com.example.vk.mvp.presenter.FriendsPresenter
import com.example.vk.mvp.view.FriendsView
import com.example.vk.ui.BackButtonListener
import com.example.vk.ui.adapter.FriendRVAdapter
import kotlinx.android.synthetic.main.fragment_friends.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FriendsFragment() : MvpAppCompatFragment(), FriendsView, BackButtonListener {

    private lateinit var friendRVAdapter: FriendRVAdapter

    @InjectPresenter
    lateinit var presenter: FriendsPresenter

    @ProvidePresenter
    fun provideFriendsPresenter(): FriendsPresenter {
        val userId = arguments?.getInt(FriendsFragment.ARG_PARAM1, 0)!!
        return FriendsPresenter(userId)
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

    override fun release() {
        VKApplication.INSTANCE.releaseFriendSubcomponent()
    }

    companion object {
        private val ARG_PARAM1 = "user_id";
        fun newInstance(userId: Int): FriendsFragment {
            val fragment = FriendsFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, userId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }


}