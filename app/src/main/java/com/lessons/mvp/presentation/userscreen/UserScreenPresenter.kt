package com.lessons.mvp.presentation.userscreen

import com.lessons.mvp.data.GithubUser
import moxy.MvpPresenter

class UserScreenPresenter(private val user: GithubUser?) : MvpPresenter<UserScreenView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (user != null)
            viewState.setLogin(user.login)
        else
            viewState.setError()
    }
}