package com.lessons.mvp

import com.lessons.mvp.userlist.IItemView
import com.lessons.mvp.userlist.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>