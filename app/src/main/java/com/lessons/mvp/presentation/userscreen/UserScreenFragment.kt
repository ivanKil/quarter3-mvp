package com.lessons.mvp.presentation.userscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lessons.mvp.data.GithubUser
import com.lessons.mvp.databinding.FragmentUserScreenBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val EXT_USER = "USER"

class UserScreenFragment : MvpAppCompatFragment(), UserScreenView {

    companion object {
        fun newInstance(user: GithubUser?): UserScreenFragment {
            return UserScreenFragment().apply {
                arguments = Bundle().apply { putParcelable(EXT_USER, user) }
            }
        }
    }

    private val presenter: UserScreenPresenter by moxyPresenter {
        UserScreenPresenter(arguments?.getParcelable(EXT_USER))
    }

    private var vb: FragmentUserScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) =
        FragmentUserScreenBinding.inflate(inflater, container, false).also { vb = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun setLogin(login: String) {
        vb?.tvLogin?.text = login
    }
}

