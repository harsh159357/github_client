package com.harsh.githubClient.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.harsh.githubClient.R
import com.harsh.githubClient.data.model.Repository
import com.harsh.githubClient.databinding.ActivityMainBinding
import com.harsh.githubClient.ui.adapter.ReposAdapter
import com.harsh.githubClient.util.hide
import com.harsh.githubClient.util.show
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
            setSupportActionBar(toolBar)

            val gridAdapter = ReposAdapter()
            gridAdapter.repoListener = this@MainActivity
            adapter = gridAdapter

            repos.layoutManager = GridLayoutManager(this@MainActivity, 1)
            reposViewModel.repos.observe(this@MainActivity) {
                it.let {
                    gridAdapter.updateRepos(it)
                    if (it.isEmpty()) {
                        binding.repos.hide()
                        binding.llNoItems.show()
                    } else {
                        binding.repos.show()
                        binding.llNoItems.hide()
                    }
                }
            }
            reposViewModel.loading.observe(this@MainActivity) {
                it.let {
                    if(it) {
                        binding.progressBar.show()
                    } else {
                        binding.progressBar.hide()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchViewItem: MenuItem = menu.findItem(R.id.action_search)
        binding.toolBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_refresh-> {
                    "Loading Harsh Sharma Repos".toast(this@MainActivity)
                    reposViewModel.refresh()
                    true
                }

                else -> {
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    super.onOptionsItemSelected(it)
                }
            }
        }

        val searchView: SearchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.queryHint = "Search Query..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if (query != null) {
                    "Searching for repositories with search query $query".toast(this@MainActivity)
                    reposViewModel.searchAction(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRepoClick(repository: Repository, position: Int) {
        "Clicked On ${repository.name}".toast(this)
    }
}