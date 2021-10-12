package com.lessons.mvp.data.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
    @SerializedName("id") val id: String,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar: String,
    @SerializedName("repos_url") val repositories: String
) : Parcelable

@Parcelize
data class GitHubUserRepos(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("forks_url") val forksUrl: String,
) : Parcelable

@Parcelize
data class GitHubUserRepoInfo(@SerializedName("id") val id: String) : Parcelable
