package com.lessons.mvp.data.user.datasource

import com.lessons.mvp.data.api.GitHubApiFactory

object UserDataSourceFactory {
    fun create(): UserDataSource = CloudUserDataSource(GitHubApiFactory.create())
}