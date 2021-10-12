package com.lessons.mvp.presentation.userrepos.repoinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.lessons.mvp.R
import com.lessons.mvp.data.user.GitHubUserRepos
import com.lessons.mvp.databinding.RepoInfoBinding
import com.lessons.mvp.presentation.abs.AbsFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

const val EXT_REPO_URL = "EXT_REPO_URL"

class RepoInfoFragment : AbsFragment(R.layout.repo_info), RepoInfoView {

    companion object {
        fun newInstance(user: GitHubUserRepos): Fragment = RepoInfoFragment().apply {
            arguments = bundleOf(Pair(EXT_REPO_URL, user))
        }
    }

    @Inject
    lateinit var repoInfoPresenterFactory: RepoInfoPresenterFactory

    private val presenter: RepoInfoPresenter by moxyPresenter {
        repoInfoPresenterFactory.create(arguments?.getParcelable(EXT_REPO_URL))
    }

    //private val vb: RepoInfoBinding by viewBinding() // тоже самое, не работает, валится ошибка
    //Fragment doesn't have view associated with it or the view has been destroyed

    private var vb: RepoInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        vb = RepoInfoBinding.inflate(inflater, container, false)
        return vb!!.root
    }

    override fun showForkCount(count: Int) {
        vb?.tvForkCount?.text =
            resources.getString(R.string.fork_count) + " " +
                    (if (count == -1) resources.getString(R.string.fork_count_unknow) else count)
    }

    override fun setError(er: Throwable) {
        Toast.makeText(
            requireActivity(), requireContext().getString(R.string.err_show_repo),
            Toast.LENGTH_LONG
        ).show();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}

