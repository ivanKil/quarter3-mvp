package com.lessons.mvp.presentation.userrepos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lessons.mvp.click
import com.lessons.mvp.data.user.GitHubUserRepos
import com.lessons.mvp.databinding.ViewRepoBinding


class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val viewBinding: ViewRepoBinding by viewBinding()

    fun bind(repo: GitHubUserRepos, delegate: RepoAdapter.Delegate?) {
        with(viewBinding) {
            repoName.text = repo.name
            root.click { delegate?.onUserPicked(repo) }
        }
    }

}