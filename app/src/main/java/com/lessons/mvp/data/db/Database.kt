package com.lessons.mvp.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lessons.mvp.data.db.user.GitHubUserDao
import com.lessons.mvp.data.db.user.GitHubUserReposDao
import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepos

@androidx.room.Database(entities = [GitHubUser::class, GitHubUserRepos::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: GitHubUserDao
    abstract val repositoryDao: GitHubUserReposDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}