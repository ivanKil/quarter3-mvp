package com.lessons.mvp.data.user

import android.content.Context
import com.lessons.mvp.data.db.Database
import com.lessons.mvp.data.network.AndroidNetworkStatus
import com.lessons.mvp.data.user.datasource.CacheUserDataSourceImpl
import com.lessons.mvp.data.user.datasource.UserDataSourceFactory

class GitHubUserRepositoryFactory(val context: Context) {

    fun create(): GitHubUserRepository =
        GitHubUserRepositoryImpl(
            UserDataSourceFactory.create(),
            AndroidNetworkStatus(context),
            CacheUserDataSourceImpl(Database.getInstance())
        )

}