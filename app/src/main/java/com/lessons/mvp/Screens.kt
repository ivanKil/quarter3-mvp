package com.lessons.mvp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lessons.mvp.data.user.GitHubUser
import com.lessons.mvp.presentation.userlist.UsersFragment
import com.lessons.mvp.presentation.userscreen.UserScreenFragment


object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = UsersFragment.newInstance()
}

class UserScreen(private var user: GitHubUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        UserScreenFragment.newInstance(user)
}

