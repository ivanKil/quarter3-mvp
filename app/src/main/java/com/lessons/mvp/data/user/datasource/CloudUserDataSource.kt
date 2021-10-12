package com.lessons.mvp.data.user.datasource

import com.lessons.mvp.data.api.GitHubApi
import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepoInfo
import com.lessons.mvp.data.user.GitHubUserRepos
import io.reactivex.rxjava3.core.Single

class CloudUserDataSource(private val gitHubApi: GitHubApi) : UserDataSource {

    override fun getUsers(): Single<List<GitHubUser>> = gitHubApi.getUsers()
    override fun getUserRepos(url: String): Single<List<GitHubUserRepos>> =
        gitHubApi.getUserRepos(url)

    override fun getRepoInfo(url: String): Single<List<GitHubUserRepoInfo>> =
        gitHubApi.getRepoInfo(url)
}