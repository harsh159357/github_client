package com.harsh.githubClient.data.repository

import com.harsh.githubClient.data.api.ApiService
import com.harsh.githubClient.data.model.PullRequest
import com.harsh.githubClient.data.model.Repository
import com.harsh.githubClient.util.GitHubClientUtil
import com.harsh.githubClient.util.Result
import retrofit2.HttpException
import timber.log.Timber

class GithubClientRepository(private val apiService: ApiService) : BaseRepository() {

    companion object {
        val TAG = GithubClientRepository::class.java.name
        const val GENERAL_ERROR_CODE = 499
        const val MY_USER_ID = "harsh159357"
    }

    suspend fun getMyRepos(): Result<ArrayList<Repository>> {
        var result: Result<ArrayList<Repository>> = handleSuccess(arrayListOf())
        try {
            val response = apiService.getUserRepos(MY_USER_ID)
            response.let {
                it.body()?.let { repos ->
                    result = handleSuccess(repos)
                }
                it.errorBody()?.let { responseErrorBody ->
                    if (responseErrorBody is HttpException) {
                        responseErrorBody.response()?.code()?.let { errorCode ->
                            result = handleException(errorCode)
                        }
                    } else result = handleException(GENERAL_ERROR_CODE)
                }
            }
        } catch (error: Exception) {
            Timber.e("$TAG - Error: ${error.message} \n ${error.stackTrace}")
            return handleException(GENERAL_ERROR_CODE)
        }
        return result
    }

    suspend fun searchRepos(searchQuery: String): Result<ArrayList<Repository>> {
        var result: Result<ArrayList<Repository>> = handleSuccess(arrayListOf())
        try {
            val response = apiService.searchRepos(searchQuery = searchQuery)
            response.let {
                it.body()?.let { repos ->
                    result = handleSuccess(repos.items)
                }
                it.errorBody()?.let { responseErrorBody ->
                    if (responseErrorBody is HttpException) {
                        responseErrorBody.response()?.code()?.let { errorCode ->
                            result = handleException(errorCode)
                        }
                    } else result = handleException(GENERAL_ERROR_CODE)
                }
            }
        } catch (error: Exception) {
            Timber.e("$TAG - Error: ${error.message} \n ${error.stackTrace}")
            return handleException(GENERAL_ERROR_CODE)
        }
        return result
    }

    suspend fun getPullRequests(path: String): Result<ArrayList<PullRequest>> {
        var result: Result<ArrayList<PullRequest>> = handleSuccess(arrayListOf())
        try {
            val response = apiService.getPullRequestsForRepo(customPath = path)
            response.let {
                it.body()?.let { pullRequests ->
                    pullRequests.forEach { pr ->
                        pr.clientCreatedAt =
                            GitHubClientUtil.getFormattedDate(pr.createdAt, "Created")
                        pr.clientClosedAt = GitHubClientUtil.getFormattedDate(pr.closedAt, "Closed")
                    }

                    result = handleSuccess(pullRequests)
                }
                it.errorBody()?.let { responseErrorBody ->
                    if (responseErrorBody is HttpException) {
                        responseErrorBody.response()?.code()?.let { errorCode ->
                            result = handleException(errorCode)
                        }
                    } else result = handleException(GENERAL_ERROR_CODE)
                }
            }
        } catch (error: Exception) {
            Timber.e("$TAG - Error: ${error.message} \n ${error.stackTrace}")
            return handleException(GENERAL_ERROR_CODE)
        }
        return result
    }

}