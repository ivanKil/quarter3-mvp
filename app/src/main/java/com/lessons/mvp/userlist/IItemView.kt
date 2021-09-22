package com.lessons.mvp.userlist

interface IItemView {
    var pos: Int
}

interface UserItemView : IItemView {
    fun setLogin(text: String)
}
