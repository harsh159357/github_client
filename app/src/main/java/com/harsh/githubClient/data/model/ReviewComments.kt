package com.harsh.githubClient.data.model

import com.google.gson.annotations.SerializedName

data class ReviewComments(
    @SerializedName("href") var href: String? = null
)