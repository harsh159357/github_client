package com.harsh.githubClient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harsh.githubClient.data.model.Repository
import com.harsh.githubClient.data.repository.BaseRepository
import com.harsh.githubClient.data.repository.GithubClientRepository
import com.harsh.githubClient.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReposViewModel(var repository: GithubClientRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    var repos: MutableLiveData<Result<ArrayList<Repository>>> = MutableLiveData()

    init {
        loadRepos()
    }

    private fun loadRepos() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getMyRepos()
            viewModelScope.launch(Dispatchers.Main) {
                result.let {
                    when (it) {
                        is Result.Success -> {
                            repos.value = Result.Success(data = it.data)
                        }
                        is Result.Error -> {
                            repos.value = Result.Error(exception = it.exception)
                        }
                        else -> {
                            repos.value = Result.Error(exception = Exception(BaseRepository.SOMETHING_WENT_WRONG))
                        }
                    }
                }
                _loading.value = false
            }
        }
    }

    private fun searchRepos(searchQuery: String) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchRepos(searchQuery)
            viewModelScope.launch(Dispatchers.Main) {
                result.let {
                    when (it) {
                        is Result.Success -> {
                            repos.value = Result.Success(data = it.data)
                        }
                        is Result.Error -> {
                            repos.value = Result.Error(exception = it.exception)
                        }
                        else -> {
                            repos.value = Result.Error(exception = Exception(BaseRepository.SOMETHING_WENT_WRONG))
                        }
                    }
                }
                _loading.value = false
            }
        }
    }

    fun searchAction(searchQuery: String) {
        repos.value = Result.Success(data = arrayListOf())
        searchRepos(searchQuery)
    }

    fun refresh() {
        repos.value = Result.Success(data = arrayListOf())
        loadRepos()
    }
}