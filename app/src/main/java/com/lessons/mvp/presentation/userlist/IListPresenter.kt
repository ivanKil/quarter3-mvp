package com.lessons.mvp

import com.lessons.mvp.presentation.userlist.IItemView
import com.lessons.mvp.presentation.userlist.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V, Int) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>