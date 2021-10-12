package com.lessons.mvp.data.user

import com.lessons.mvp.data.network.INetworkStatus
import com.lessons.mvp.data.user.datasource.CacheUserDataSource
import com.lessons.mvp.data.user.datasource.UserDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GitHubUserRepositoryImpl @Inject constructor(
    private val cloud: UserDataSource, private val networkStatus: INetworkStatus,
    private val cache: CacheUserDataSource
) : GitHubUserRepository {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            cloud.getUsers()
                .flatMap { users -> cache.retainUsers(users) }
        } else
            cache.getUsers()
    }

    override fun getUserRepos(user: GitHubUser): Single<List<GitHubUserRepos>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.repositories?.let { url ->
                    cloud.getUserRepos(url)
                        .flatMap { repositories ->
                            cache.retainRepos(repositories.apply {
                                forEach {
                                    it.userId = user.id
                                }
                            })
                        }
                } ?: Single.error<List<GitHubUserRepos>>(RuntimeException("User has no repos url"))
            } else
                cache.getUserRepos(user)
        }

    override fun getRepoInfo(repo: GitHubUserRepos): Single<Int> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                repo.forksUrl?.let { forksUrl ->
                    cloud.getRepoInfo(forksUrl)
                        .flatMap { repositories ->
                            repo.forksCount = repositories.size
                            cache.retainRepo(repo)
                            Single.just(repositories.size)
                        }
                } ?: Single.just(-1)
            } else
                cache.getRepoInfo(repo)
        }
}