package com.lessons.mvp.presentation.userrepos

import com.lessons.mvp.data.user.GitHubUserRepos
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface ReposView : MvpView {
    fun showRepos(users: List<GitHubUserRepos>)
    fun setError(er: Throwable)
}