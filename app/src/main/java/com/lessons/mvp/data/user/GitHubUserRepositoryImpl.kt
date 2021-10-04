package com.lessons.mvp.data.user

import com.lessons.mvp.data.db.Database
import com.lessons.mvp.data.network.INetworkStatus
import com.lessons.mvp.data.user.datasource.UserDataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitHubUserRepositoryImpl(
    private val cloud: UserDataSource, val networkStatus: INetworkStatus, val db: Database
) : GitHubUserRepository {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            cloud.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        db.userDao.insert(users)
                        users
                    }
                }
        } else
            Single.fromCallable { db.userDao.getAll() }

    }

    override fun getUserRepos(user: GitHubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.repositories?.let { url ->
                    cloud.getUserRepos(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomRepos = repositories.map {
                                    GitHubUserRepos(
                                        it.id,
                                        it.name ?: "",
                                        null,
                                        user.id, -1
                                    )
                                }
                                db.repositoryDao.insert(roomRepos)
                                repositories
                            }
                        }
                } ?: Single.error<List<GitHubUserRepos>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    db.repositoryDao.findForUser(user.id)
                }
            }
        }

    override fun getRepoInfo(repo: GitHubUserRepos): Single<Int> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                repo.forksUrl?.let { forksUrl ->
                    cloud.getRepoInfo(forksUrl)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                var repoFromDB = db.repositoryDao.getById(repo.id)
                                repoFromDB.forksCount = repositories.size
                                db.repositoryDao.update(repoFromDB)
                                repositories.size
                            }
                        }
                } ?: Single.just(0)
            } else {
                Single.fromCallable {
                    db.repositoryDao.getById(repo.id).forksCount
                }
            }
        }
}