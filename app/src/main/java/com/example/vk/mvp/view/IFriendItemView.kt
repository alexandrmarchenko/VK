package com.example.vk.mvp.view

import com.example.vk.mvp.model.entity.user.Friend

interface IFriendItemView: IItemView {
    fun init(user: Friend)
}