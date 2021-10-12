package com.lessons.mvp.presentation.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.lessons.mvp.BackButtonListener
import com.lessons.mvp.R
import com.lessons.mvp.data.user.GitHubUserRepository
import com.lessons.mvp.databinding.FragmentUsersBinding
import com.lessons.mvp.presentation.abs.AbsFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : AbsFragment(R.layout.fragment_users), UsersView, BackButtonListener {
    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var gitHubUserRepository: GitHubUserRepository
 
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(gitHubUserRepository, router)
    }
    private var adapter: UsersRVAdapter? = null

    private val vb: FragmentUsersBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).root

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(er: Throwable) {
        Toast.makeText(
            requireActivity(), requireContext().getString(R.string.err_get_list),
            Toast.LENGTH_LONG
        ).show();
    }

    override fun backPressed() = presenter.backPressed()
}

