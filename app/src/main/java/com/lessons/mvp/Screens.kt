package com.lessons.mvp

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lessons.mvp.data.GithubUser
import com.lessons.mvp.presentation.userlist.UsersFragment
import com.lessons.mvp.presentation.userscreen.UserScreenFragment


object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory) = UsersFragment.newInstance()
}

class UserScreen(private var user: GithubUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory) = UserScreenFragment.newInstance(user)
}

