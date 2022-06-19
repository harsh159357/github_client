package com.harsh.githubClient.data.model

import com.google.gson.annotations.SerializedName

data class SearchRepository(
    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("incomplete_results") var incompleteResults: Boolean? = null,
    @SerializedName("items") var items: ArrayList<Repository> = arrayListOf()
)