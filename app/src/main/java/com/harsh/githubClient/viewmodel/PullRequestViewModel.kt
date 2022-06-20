package com.harsh.githubClient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harsh.githubClient.data.model.PullRequest
import com.harsh.githubClient.data.repository.GithubClientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullRequestViewModel(var repository: GithubClientRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    var pullRequests: MutableLiveData<ArrayList<PullRequest>> = MutableLiveData()

    init {
        _loading.value = false
    }

    private fun loadPRs(path: String) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPullRequests(path)
            viewModelScope.launch(Dispatchers.Main) {
                pullRequests.value = result.extractData
                _loading.value = false
            }
        }
    }

    fun getPRsForRepo(path: String) {
        loadPRs(path)
    }
}