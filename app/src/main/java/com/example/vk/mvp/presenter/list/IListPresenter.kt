package com.example.vk.mvp.presenter.list

import com.example.vk.mvp.view.IItemView

interface IListPresenter<V : IItemView> {
    fun onItemClick(view: V)
    fun bindView(view: V)
    fun getCount(): Int
}