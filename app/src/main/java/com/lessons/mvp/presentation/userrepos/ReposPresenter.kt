package com.lessons.mvp.presentation.userrepos

import com.github.terrakok.cicerone.Router
import com.lessons.mvp.RepoInfoScreen
import com.lessons.mvp.addTo
import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepos
import com.lessons.mvp.data.user.GitHubUserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ReposPresenter @AssistedInject constructor(
    @Assisted private val user: GitHubUser?,
    private val usersRepo: GitHubUserRepository,
    private val router: Router
) : MvpPresenter<ReposView>() {
    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        loadData()
    }

    private fun loadData() {
        user?.let {
            usersRepo.getUserRepos(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    viewState::showRepos,
                    viewState::setError
                )
                .addTo(disposables)
        }
    }

    fun displayRepoInfo(repo: GitHubUserRepos) {
        router.navigateTo(RepoInfoScreen(repo))
    }

    override fun onDestroy() {
        disposables.clear()
    }
}