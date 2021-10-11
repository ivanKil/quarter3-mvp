package com.lessons.mvp.presentation.userrepos

import com.lessons.mvp.data.user.GitHubUser
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ReposPresenterFactory {
    fun create(user: GitHubUser?): ReposPresenter
}