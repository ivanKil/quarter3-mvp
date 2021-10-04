package com.lessons.mvp.presentation.userrepos.repoinfo

import com.lessons.mvp.addTo
import com.lessons.mvp.data.user.GitHubUserRepos
import com.lessons.mvp.data.user.GitHubUserRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class RepoInfoPresenter(
    private val repo: GitHubUserRepos?,
    private val usersRepo: GitHubUserRepository
) : MvpPresenter<RepoInfoView>() {
    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        loadData()
    }

    private fun loadData() {
        repo?.let {
            usersRepo.getRepoInfo(repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { viewState.showForkCount(it) },
                    viewState::setError
                )
                .addTo(disposables)
        }
    }

    override fun onDestroy() {
        disposables.clear()
    }
}