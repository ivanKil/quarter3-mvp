package com.lessons.mvp.data.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lessons.mvp.data.user.GitHubUser
import io.reactivex.rxjava3.core.Single

@Dao
interface GitHubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GitHubUser>)

    @Query("SELECT * FROM GitHubUser")
    fun getAll(): Single<List<GitHubUser>>
}