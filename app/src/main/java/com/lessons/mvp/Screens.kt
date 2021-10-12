package com.lessons.mvp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.data.user.GitHubUserRepos
import com.lessons.mvp.presentation.userlist.UsersFragment
import com.lessons.mvp.presentation.userrepos.ReposFragment
import com.lessons.mvp.presentation.userrepos.repoinfo.RepoInfoFragment


object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = UsersFragment.newInstance()
}

class ReposScreen(private var user: GitHubUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        ReposFragment.newInstance(user)
}

class RepoInfoScreen(private var repo: GitHubUserRepos) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        RepoInfoFragment.newInstance(repo)
}

