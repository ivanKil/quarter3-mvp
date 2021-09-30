package ru.gb.gb_popular_libs.data.user

import com.lessons.mvp.data.user.GitHubUser
import io.reactivex.rxjava3.core.Single
import ru.gb.gb_popular_libs.data.user.datasource.UserDataSource

class GitHubUserRepositoryImpl(private val cloud: UserDataSource) : GitHubUserRepository {

    override fun getUsers(): Single<List<GitHubUser>> = cloud.getUsers()
}