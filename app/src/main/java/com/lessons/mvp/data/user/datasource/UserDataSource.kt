package com.lessons.mvp.data.user.datasource

import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepoInfo
import com.lessons.mvp.data.user.GitHubUserRepos
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUserRepos(url: String): Single<List<GitHubUserRepos>>
    fun getRepoInfo(url: String): Single<List<GitHubUserRepoInfo>>
}