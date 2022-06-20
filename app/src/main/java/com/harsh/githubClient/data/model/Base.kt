package com.harsh.githubClient.data.model

import com.google.gson.annotations.SerializedName

data class Base(
    @SerializedName("label") var label: String? = null,
    @SerializedName("ref") var ref: String? = null,
    @SerializedName("sha") var sha: String? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("repo") var repo: Repo? = Repo()
)