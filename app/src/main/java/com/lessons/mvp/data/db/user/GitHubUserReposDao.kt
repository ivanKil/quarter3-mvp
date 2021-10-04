package com.lessons.mvp.data.db.user

import androidx.room.*
import com.lessons.mvp.data.user.GitHubUserRepos

@Dao
interface GitHubUserReposDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GitHubUserRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: GitHubUserRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GitHubUserRepos>)

    @Update
    fun update(user: GitHubUserRepos)

    @Update
    fun update(vararg repo: GitHubUserRepos)

    @Update
    fun update(users: List<GitHubUserRepos>)

    @Delete
    fun delete(user: GitHubUserRepos)

    @Delete
    fun delete(vararg users: GitHubUserRepos)

    @Delete
    fun delete(users: List<GitHubUserRepos>)

    @Query("SELECT * FROM GitHubUserRepos")
    fun getAll(): List<GitHubUserRepos>

    @Query("SELECT * FROM GitHubUserRepos WHERE userId = :userId")
    fun findForUser(userId: String): List<GitHubUserRepos>

    @Query("SELECT * FROM GitHubUserRepos WHERE id = :id")
    fun getById(id: Int): GitHubUserRepos
}

