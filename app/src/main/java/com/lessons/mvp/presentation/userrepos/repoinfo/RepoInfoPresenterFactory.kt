package com.lessons.mvp.presentation.userrepos.repoinfo

import com.lessons.mvp.data.user.GitHubUserRepos
import dagger.assisted.AssistedFactory

@AssistedFactory
interface RepoInfoPresenterFactory {
    fun create(repo: GitHubUserRepos?): RepoInfoPresenter
}