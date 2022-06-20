package com.harsh.githubClient.data.model

import com.google.gson.annotations.SerializedName

data class MileStone(
    @SerializedName("url") var url: String? = null,
    @SerializedName("html_url") var htmlUrl: String? = null,
    @SerializedName("labels_url") var labelsUrl: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("number") var number: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("creator") var creator: Creator? = Creator(),
    @SerializedName("open_issues") var openIssues: Int? = null,
    @SerializedName("closed_issues") var closedIssues: Int? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("due_on") var dueOn: String? = null,
    @SerializedName("closed_at") var closedAt: String? = null
)