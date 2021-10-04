package com.lessons.mvp.data.db.user

import androidx.room.*
import com.lessons.mvp.data.user.GitHubUser

@Dao
interface GitHubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: GitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GitHubUser>)

    @Update
    fun update(user: GitHubUser)

    @Update
    fun update(vararg users: GitHubUser)

    @Update
    fun update(users: List<GitHubUser>)

    @Delete
    fun delete(user: GitHubUser)

    @Delete
    fun delete(vararg users: GitHubUser)

    @Delete
    fun delete(users: List<GitHubUser>)

    @Query("SELECT * FROM GitHubUser")
    fun getAll(): List<GitHubUser>

    @Query("SELECT * FROM GitHubUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): GitHubUser?


}