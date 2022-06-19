package com.harsh.githubClient.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.harsh.githubClient.R
import com.harsh.githubClient.data.model.Repository
import com.harsh.githubClient.databinding.ActivityMainBinding
import com.harsh.githubClient.ui.adapter.ReposAdapter
import com.harsh.githubClient.util.toast
import com.harsh.githubClient.viewmodel.ReposViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), ReposAdapter.RepoListener {

    companion object {
        fun createIntent(context: Context): Intent =
            Intent(context, MainActivity::class.java)
    }

    private val reposViewModel by inject<ReposViewModel> { parametersOf(this) }

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply {
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            toolBar.title = getString(R.string.repositories)

            val gridAdapter = ReposAdapter()
            gridAdapter.repoListener = this@MainActivity
            adapter = gridAdapter

            repos.layoutManager = GridLayoutManager(this@MainActivity, 1)
            reposViewModel.repos.observe(this@MainActivity) {
                it.let {
                    gridAdapter.updateRepos(it)
                    if (it.isEmpty()) {
                        binding.repos.visibility = View.GONE
                        binding.llNoItems.visibility = View.VISIBLE
                    } else {
                        binding.repos.visibility = View.VISIBLE
                        binding.llNoItems.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onRepoClick(repository: Repository, position: Int) {
        "Clicked On ${repository.name}".toast(this)
    }
}