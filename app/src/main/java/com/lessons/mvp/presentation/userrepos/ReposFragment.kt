package com.lessons.mvp.presentation.userrepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.lessons.mvp.App
import com.lessons.mvp.R
import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepos
import com.lessons.mvp.data.user.GitHubUserRepositoryFactory
import com.lessons.mvp.databinding.ViewReposBinding
import com.lessons.mvp.presentation.userrepos.adapter.RepoAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val EXT_USER = "USER"

class ReposFragment : MvpAppCompatFragment(), ReposView, RepoAdapter.Delegate {

    companion object {
        fun newInstance(user: GitHubUser?): Fragment = ReposFragment().apply {
            arguments = bundleOf(Pair(EXT_USER, user))
        }
    }

    private val presenter: ReposPresenter by moxyPresenter {
        ReposPresenter(
            arguments?.getParcelable(EXT_USER),
            GitHubUserRepositoryFactory(requireContext()).create(),
            App.instance.router
        )
    }

    //private val vb: ViewReposBinding by viewBinding() // TODO это не работает, валится ошибка
    //Fragment doesn't have view associated with it or the view has been destroyed

    private var vb: ViewReposBinding? = null
    private val repoAdapter = RepoAdapter(delegate = this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        vb = ViewReposBinding.inflate(inflater, container, false)
        vb?.repos?.adapter = repoAdapter
        return vb!!.root
    }

    override fun showRepos(users: List<GitHubUserRepos>) {

        repoAdapter.submitList(users)
        repoAdapter.notifyDataSetChanged()
    }

    override fun setError(er: Throwable) {
        Toast.makeText(
            requireActivity(), requireContext().getString(R.string.err_show_repo),
            Toast.LENGTH_LONG
        ).show();
    }

    override fun onUserPicked(repo: GitHubUserRepos) {
        presenter.displayRepoInfo(repo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}

