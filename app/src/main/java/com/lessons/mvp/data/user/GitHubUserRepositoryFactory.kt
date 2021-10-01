package com.lessons.mvp.data.user

import com.lessons.mvp.data.user.datasource.UserDataSourceFactory

object GitHubUserRepositoryFactory {

    fun create(): GitHubUserRepository =
        GitHubUserRepositoryImpl(
            UserDataSourceFactory.create()
        )

}