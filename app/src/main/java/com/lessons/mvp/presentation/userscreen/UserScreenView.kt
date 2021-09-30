package com.lessons.mvp.presentation.userscreen

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface UserScreenView : MvpView {
    fun setLogin(login: String)
    fun setError()
}