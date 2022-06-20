package com.harsh.githubClient.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.harsh.githubClient.R
import com.harsh.githubClient.data.model.PullRequest
import com.harsh.githubClient.databinding.ActivityPullRequestsBinding
import com.harsh.githubClient.ui.adapter.PullRequestsAdapter
import com.harsh.githubClient.util.GitHubClientUtil
import com.harsh.githubClient.util.hide
import com.harsh.githubClient.util.show
import com.harsh.githubClient.util.toast
import com.harsh.githubClient.viewmodel.PullRequestViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PullRequestActivity : AppCompatActivity(), PullRequestsAdapter.PRListener {

    companion object {
        fun createIntent(context: Context): Intent =
            Intent(context, PullRequestActivity::class.java)
    }

    private val pullRequestViewModel by inject<PullRequestViewModel> { parametersOf(this) }

    private val binding: ActivityPullRequestsBinding by lazy {
        DataBindingUtil.setContentView<ActivityPullRequestsBinding>(
            this,
            R.layout.activity_pull_requests
        ).apply {
            lifecycleOwner = this@PullRequestActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            setSupportActionBar(toolBar)

            val gridAdapter = PullRequestsAdapter()
            gridAdapter.prListener = this@PullRequestActivity
            adapter = gridAdapter

            pullRequests.layoutManager = GridLayoutManager(this@PullRequestActivity, 1)
            pullRequestViewModel.pullRequests.observe(this@PullRequestActivity) {
                it.let {
                    gridAdapter.updateRepos(it)
                    if (it.isEmpty()) {
                        binding.pullRequests.hide()
                        binding.llNoItems.show()
                    } else {
                        binding.pullRequests.show()
                        binding.llNoItems.hide()
                    }
                }
            }
            pullRequestViewModel.loading.observe(this@PullRequestActivity) {
                it.let {
                    if (it) {
                        binding.progressBar.show()
                    } else {
                        binding.progressBar.hide()
                    }
                }
            }
            intent.getStringExtra(GitHubClientUtil.REPO_PATH)
                ?.let {
                    pullRequestViewModel.getPRsForRepo(it)
                    toolBar.title = it.split("/")[1]
                }
        }
    }

    override fun onPRClick(pullRequest: PullRequest, position: Int) {
        "Clicked On PR Number ${pullRequest.number}".toast(this)
    }
}