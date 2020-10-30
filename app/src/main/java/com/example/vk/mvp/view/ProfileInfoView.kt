package com.example.vk.mvp.view

import com.example.vk.mvp.model.entity.profileInfo.ProfileInfoDetails
import com.example.vk.mvp.model.entity.user.UserDetail
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ProfileInfoView : MvpView {
    fun setProfileInfo(profileInfoDetails: ProfileInfoDetails)
    fun setUserInfo(userDetail: UserDetail)
    fun openFriendsFragment(userId: Int)
}