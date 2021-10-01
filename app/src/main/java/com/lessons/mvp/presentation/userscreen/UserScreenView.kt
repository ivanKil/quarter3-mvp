package com.lessons.mvp.presentation.userscreen

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserScreenView : MvpView {
    fun setLogin(login: String)
}