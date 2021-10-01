package com.lessons.mvp.presentation.userrepos.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.lessons.mvp.data.user.GitHubUserRepos

object RepoDiff : DiffUtil.ItemCallback<GitHubUserRepos>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: GitHubUserRepos, newItem: GitHubUserRepos): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: GitHubUserRepos, newItem: GitHubUserRepos): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: GitHubUserRepos, newItem: GitHubUserRepos) = payload

}

