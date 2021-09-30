package com.lessons.mvp.presentation.userlist

import com.github.terrakok.cicerone.Router
import com.lessons.mvp.IUserListPresenter
import com.lessons.mvp.UserScreen
import com.lessons.mvp.addTo
import com.lessons.mvp.data.user.GitHubUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.gb.gb_popular_libs.data.user.GitHubUserRepository

class UsersPresenter(
    private val usersRepo: GitHubUserRepository,
    private val router: Router
) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView, Int) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.avatar?.let { view.loadAvatar(it) }
        }
    }

    private var disposables = CompositeDisposable()
    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { _, pos -> displayUser(pos) }
    }

    fun displayUser(position: Int) {
        router.navigateTo(UserScreen(usersListPresenter.users[position]))
    }

    private fun loadData() {
        usersRepo.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::setData, viewState::showError)
            .addTo(disposables)
    }

    private fun setData(list: List<GitHubUser>) {
        usersListPresenter.users.addAll(list)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }

}
