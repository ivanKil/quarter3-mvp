package ru.gb.gb_popular_libs.data.user

import com.lessons.mvp.data.user.GitHubUser
import io.reactivex.rxjava3.core.Single

interface GitHubUserRepository {

    fun getUsers(): Single<List<GitHubUser>>

}