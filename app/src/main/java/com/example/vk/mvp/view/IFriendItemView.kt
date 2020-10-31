package com.example.vk.mvp.view

import com.example.vk.mvp.model.entity.user.UserDetail

interface IFriendItemView: IItemView {
    fun init(user: UserDetail)
}