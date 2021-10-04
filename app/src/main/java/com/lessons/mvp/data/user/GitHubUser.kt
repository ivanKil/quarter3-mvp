package com.lessons.mvp.data.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity()
data class GitHubUser(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar: String,
    @Ignore @SerializedName("repos_url") val repositories: String?
) : Parcelable {
    constructor(
        id: String,
        login: String,
        avatar: String
    ) : this(id, login, avatar, null)
}

@Parcelize
@Entity(
    foreignKeys = [ForeignKey(
        entity = GitHubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class GitHubUserRepos(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @Ignore @SerializedName("forks_url") val forksUrl: String?,
    var userId: String,
    var forksCount: Int,
) : Parcelable {
    constructor(
        id: Int,
        name: String,
        userId: String,
        forksCount: Int
    ) : this(id, name, null, userId, forksCount)
}

@Parcelize
data class GitHubUserRepoInfo(@SerializedName("id") val id: String) : Parcelable
