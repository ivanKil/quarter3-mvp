package com.lessons.mvp.presentation.userscreen

import com.lessons.mvp.data.user.GitHubUser
import moxy.MvpPresenter

class UserScreenPresenter(private val user: GitHubUser?) : MvpPresenter<UserScreenView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (user != null)
            viewState.setLogin(user.login)
        else
            viewState.setError()
    }
}