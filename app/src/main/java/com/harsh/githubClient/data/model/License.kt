package com.harsh.githubClient.data.model

import com.google.gson.annotations.SerializedName

data class License(
    @SerializedName("key") var key: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("spdx_id") var spdxId: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("node_id") var nodeId: String? = null
)