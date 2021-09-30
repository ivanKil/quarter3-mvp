package ru.gb.gb_popular_libs.data.user.datasource

import com.lessons.mvp.data.user.GitHubUser
import io.reactivex.rxjava3.core.Single

interface UserDataSource {

    fun getUsers(): Single<List<GitHubUser>>

}