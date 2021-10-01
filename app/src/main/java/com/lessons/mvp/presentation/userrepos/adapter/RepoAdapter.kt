package com.lessons.mvp.presentation.userrepos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lessons.mvp.R
import com.lessons.mvp.data.user.GitHubUserRepos

class RepoAdapter(private val delegate: Delegate?) :
    ListAdapter<GitHubUserRepos, RepoViewHolder>(RepoDiff) {

    interface Delegate {
        fun onUserPicked(repo: GitHubUserRepos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        RepoViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_repo, parent, false)
        )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}