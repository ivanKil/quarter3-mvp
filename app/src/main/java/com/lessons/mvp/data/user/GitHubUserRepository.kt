package com.lessons.mvp.data.user

import io.reactivex.rxjava3.core.Single

interface GitHubUserRepository {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUserRepos(url: String): Single<List<GitHubUserRepos>>
    fun getRepoInfo(url: String): Single<List<GitHubUserRepoInfo>>
}