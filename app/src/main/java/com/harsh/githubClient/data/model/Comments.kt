package com.harsh.githubClient.data.model

import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("href") var href: String? = null
)