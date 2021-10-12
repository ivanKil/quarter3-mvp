package com.lessons.mvp.data.api

import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepoInfo
import com.lessons.mvp.data.user.GitHubUserRepos
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubApi {

    @GET("/users")
    fun getUsers(@Query("since") since: Int? = null): Single<List<GitHubUser>>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GitHubUserRepos>>

    @GET
    fun getRepoInfo(@Url url: String): Single<List<GitHubUserRepoInfo>>

}