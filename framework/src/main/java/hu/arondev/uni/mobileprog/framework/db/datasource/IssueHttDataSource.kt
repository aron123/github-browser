package hu.arondev.uni.mobileprog.framework.db.datasource

import android.content.Context
import hu.arondev.uni.mobileprog.core.data.IssueDataSource
import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.framework.db.GithubApiClient

class IssueHttDataSource(context: Context) : IssueDataSource {
    private val issueDao = GithubApiClient.getInstance(context).issueDao()

    override suspend fun getIssuesOfRepo(repoFullName: String): List<Issue> {
        TODO("Not yet implemented")
    }

    override suspend fun getOneIssueOfRepo(repoFullName: String, id: Int): Issue {
        TODO("Not yet implemented")
    }

    override suspend fun getCommentsOfIssue(repoFullName: String, id: Int): List<IssueComment> {
        TODO("Not yet implemented")
    }
}