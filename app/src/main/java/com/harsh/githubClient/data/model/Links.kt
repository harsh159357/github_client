package com.harsh.githubClient.data.model

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self") var self: Self? = Self(),
    @SerializedName("html") var html: Html? = Html(),
    @SerializedName("issue") var issue: Issue? = Issue(),
    @SerializedName("comments") var comments: Comments? = Comments(),
    @SerializedName("review_comments") var reviewComments: ReviewComments? = ReviewComments(),
    @SerializedName("review_comment") var reviewComment: ReviewComment? = ReviewComment(),
    @SerializedName("commits") var commits: Commits? = Commits(),
    @SerializedName("statuses") var statuses: Statuses? = Statuses()
)