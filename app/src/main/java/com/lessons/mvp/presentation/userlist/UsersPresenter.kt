package com.lessons.mvp.presentation.userlist

import com.github.terrakok.cicerone.Router
import com.lessons.mvp.IUserListPresenter
import com.lessons.mvp.UserScreen
import com.lessons.mvp.data.GithubUser
import com.lessons.mvp.data.GithubUsersRepo
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView, Int) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    private var disposable: Disposable? = null
    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView, pos ->
            router.navigateTo(UserScreen(usersListPresenter.users[pos]))
        }
    }

    private fun loadData() {
        disposable = usersRepo.getUsers().subscribe(::setData, viewState::showError)
    }

    private fun setData(list: List<GithubUser>) {
        usersListPresenter.users.addAll(list)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun destroyView(view: UsersView?) {
        super.destroyView(view)
        disposable?.dispose()
    }

}
