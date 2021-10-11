package com.lessons.mvp.presentation.userrepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.lessons.mvp.R
import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepos
import com.lessons.mvp.databinding.ViewReposBinding
import com.lessons.mvp.presentation.abs.AbsFragment
import com.lessons.mvp.presentation.userrepos.adapter.RepoAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject

const val EXT_USER = "USER"

class ReposFragment : AbsFragment(R.layout.view_repos), ReposView, RepoAdapter.Delegate {

    @Inject
    lateinit var reposPresenterFactory: ReposPresenterFactory

    companion object {
        fun newInstance(user: GitHubUser?): Fragment = ReposFragment().apply {
            arguments = bundleOf(Pair(EXT_USER, user))
        }
    }

    private val presenter: ReposPresenter by moxyPresenter {
        reposPresenterFactory.create(arguments?.getParcelable(EXT_USER))
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

