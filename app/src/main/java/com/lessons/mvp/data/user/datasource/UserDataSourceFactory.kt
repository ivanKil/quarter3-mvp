package ru.gb.gb_popular_libs.data.user.datasource

import com.lessons.mvp.data.api.GitHubApiFactory
import com.lessons.mvp.data.user.datasource.CloudUserDataSource

object UserDataSourceFactory {

    fun create(): UserDataSource = CloudUserDataSource(GitHubApiFactory.create())

}