package com.harsh.githubClient.data.model

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("url") var url: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("html_url") var htmlUrl: String? = null,
    @SerializedName("diff_url") var diffUrl: String? = null,
    @SerializedName("patch_url") var patchUrl: String? = null,
    @SerializedName("issue_url") var issueUrl: String? = null,
    @SerializedName("number") var number: Int? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("locked") var locked: Boolean? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("body") var body: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("closed_at") var closedAt: String? = null,
    @SerializedName("merge_commit_sha") var mergeCommitSha: String? = null,
    @SerializedName("assignee") var assignee: Assignee? = null,
    @SerializedName("assignees") var assignees: ArrayList<Assignee> = arrayListOf(),
    @SerializedName("requested_reviewers") var requestedReviewers: ArrayList<RequestedReviewers> = arrayListOf(),
    @SerializedName("requested_teams") var requestedTeams: ArrayList<String> = arrayListOf(),
    @SerializedName("labels") var labels: ArrayList<Labels> = arrayListOf(),
    @SerializedName("milestone") var milestone: MileStone? = null,
    @SerializedName("draft") var draft: Boolean? = null,
    @SerializedName("commits_url") var commitsUrl: String? = null,
    @SerializedName("review_comments_url") var reviewCommentsUrl: String? = null,
    @SerializedName("review_comment_url") var reviewCommentUrl: String? = null,
    @SerializedName("comments_url") var commentsUrl: String? = null,
    @SerializedName("statuses_url") var statusesUrl: String? = null,
    @SerializedName("head") var head: Head? = Head(),
    @SerializedName("base") var base: Base? = Base(),
    @SerializedName("_links") var Links: Links? = Links(),
    @SerializedName("author_association") var authorAssociation: String? = null,
    @SerializedName("auto_merge") var autoMerge: String? = null,
    @SerializedName("active_lock_reason") var activeLockReason: String? = null,
    var clientCreatedAt: String? = null,
    var clientUpdatedAt: String? = null,
    var clientClosedAt: String? = null,
    var clientMergedAt: String? = null,
)