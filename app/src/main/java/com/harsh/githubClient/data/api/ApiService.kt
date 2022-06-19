package com.harsh.githubClient.data.api

import com.harsh.githubClient.data.model.Repository
import com.harsh.githubClient.data.model.SearchRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{user_id}/repos")
    suspend fun getUserRepos(@Path("user_id") userId: String): Response<ArrayList<Repository>>

    @GET("search/repositories")
    suspend fun searchRepos(
        @Query("q") searchQuery: String,
        @Query("per_page") perPage: String = "100"
    ): Response<SearchRepository>

}