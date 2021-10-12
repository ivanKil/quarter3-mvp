package com.lessons.mvp.data.db

import androidx.room.RoomDatabase
import com.lessons.mvp.data.db.user.GitHubUserDao
import com.lessons.mvp.data.db.user.GitHubUserReposDao
import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepos

@androidx.room.Database(entities = [GitHubUser::class, GitHubUserRepos::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: GitHubUserDao
    abstract val repositoryDao: GitHubUserReposDao
    
}