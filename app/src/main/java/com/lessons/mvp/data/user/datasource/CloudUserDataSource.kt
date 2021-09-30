package com.lessons.mvp.data.user.datasource

import com.lessons.mvp.data.api.GitHubApi
import com.lessons.mvp.data.user.GitHubUser
import io.reactivex.rxjava3.core.Single
import ru.gb.gb_popular_libs.data.user.datasource.UserDataSource

class CloudUserDataSource(private val gitHubApi: GitHubApi) : UserDataSource {

    override fun getUsers(): Single<List<GitHubUser>> = gitHubApi.getUsers()

}