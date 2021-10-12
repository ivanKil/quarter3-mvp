package com.lessons.mvp.data.user.datasource

import com.lessons.mvp.data.db.Database
import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepos
import io.reactivex.rxjava3.core.Single

class CacheUserDataSourceImpl(private val db: Database) : CacheUserDataSource {
    override fun retainUsers(users: List<GitHubUser>): Single<List<GitHubUser>> {
        db.userDao.insert(users)
        return Single.fromCallable { users }
    }

    override fun retainRepo(repo: GitHubUserRepos) {
        var repoFromDB = db.repositoryDao.getById(repo.id)
        repoFromDB?.let {
            db.repositoryDao.update(repo)
        }
    }

    override fun retainRepos(repos: List<GitHubUserRepos>): Single<List<GitHubUserRepos>> {
        db.repositoryDao.insert(repos)
        return Single.fromCallable { repos }
    }

    override fun getUserRepos(user: GitHubUser): Single<List<GitHubUserRepos>> {
        return Single.fromCallable { db.repositoryDao.findForUser(user.id) }
    }

    override fun getUsers(): Single<List<GitHubUser>> = db.userDao.getAll()

    override fun getRepoInfo(repo: GitHubUserRepos): Single<Int> {
        return Single.fromCallable { db.repositoryDao.getById(repo.id)?.forksCount }
    }
}