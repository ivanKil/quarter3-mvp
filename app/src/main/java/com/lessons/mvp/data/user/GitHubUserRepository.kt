package com.lessons.mvp.data.user

import io.reactivex.rxjava3.core.Single

interface GitHubUserRepository {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUserRepos(user: GitHubUser): Single<List<GitHubUserRepos>>
    fun getRepoInfo(repo: GitHubUserRepos): Single<Int>
}