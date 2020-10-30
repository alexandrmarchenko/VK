package com.example.vk.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface FriendsView: MvpView {
    fun init()
    fun updateList()
}