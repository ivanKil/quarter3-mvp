package com.lessons.mvp.presentation.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lessons.mvp.App
import com.lessons.mvp.BackButtonListener
import com.lessons.mvp.R
import com.lessons.mvp.data.GithubUsersRepo
import com.lessons.mvp.databinding.FragmentUsersBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router)
    }
    private var adapter: UsersRVAdapter? = null

    private val vb: FragmentUsersBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).root

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
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

