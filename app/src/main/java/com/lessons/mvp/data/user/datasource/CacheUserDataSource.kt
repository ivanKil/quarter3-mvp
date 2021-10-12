package com.lessons.mvp.data.user.datasource

import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepos
import io.reactivex.rxjava3.core.Single

interface CacheUserDataSource {
    fun retainUsers(users: List<GitHubUser>): Single<List<GitHubUser>>
    fun retainRepo(repo: GitHubUserRepos)
    fun retainRepos(repos: List<GitHubUserRepos>): Single<List<GitHubUserRepos>>
    fun getUserRepos(user: GitHubUser): Single<List<GitHubUserRepos>>
    fun getRepoInfo(repo: GitHubUserRepos): Single<Int>
    fun getUsers(): Single<List<GitHubUser>>
}