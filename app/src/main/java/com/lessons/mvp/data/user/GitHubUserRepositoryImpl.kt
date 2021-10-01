package com.lessons.mvp.data.user

import com.lessons.mvp.data.user.datasource.UserDataSource
import io.reactivex.rxjava3.core.Single

class GitHubUserRepositoryImpl(private val cloud: UserDataSource) : GitHubUserRepository {

    override fun getUsers(): Single<List<GitHubUser>> = cloud.getUsers()
    override fun getUserRepos(url: String): Single<List<GitHubUserRepos>> =
        cloud.getUserRepos(url)

    override fun getRepoInfo(url: String): Single<List<GitHubUserRepoInfo>> = cloud.getRepoInfo(url)
}