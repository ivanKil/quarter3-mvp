package com.lessons.mvp.presentation.userlist

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)//TODO @SingleState не работает
interface UsersView : MvpView {
    fun init()
    fun updateList()
    fun showError(er: Throwable)
}