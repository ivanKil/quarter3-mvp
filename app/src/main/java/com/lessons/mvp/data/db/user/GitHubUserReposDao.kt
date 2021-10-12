package com.lessons.mvp.data.db.user

import androidx.room.*
import com.lessons.mvp.data.user.GitHubUserRepos

@Dao
interface GitHubUserReposDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<GitHubUserRepos>)

    @Update
    fun update(repo: GitHubUserRepos)

    @Query("SELECT * FROM GitHubUserRepos WHERE userId = :userId")
    fun findForUser(userId: String): List<GitHubUserRepos>

    @Query("SELECT * FROM GitHubUserRepos WHERE id = :id")
    fun getById(id: Int): GitHubUserRepos?
}

