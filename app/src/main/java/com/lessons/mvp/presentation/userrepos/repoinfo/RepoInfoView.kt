package com.lessons.mvp.presentation.userrepos.repoinfo

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface RepoInfoView : MvpView {
    fun showForkCount(count: Int)
    fun setError(er: Throwable)
}