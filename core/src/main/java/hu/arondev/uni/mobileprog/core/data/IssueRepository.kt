package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.IssueComment

class IssueRepository(private val issueDataSource: IssueDataSource) {
    suspend fun getIssuesOfRepo(repoFullName: String): List<Issue> = issueDataSource.getIssuesOfRepo(repoFullName)
    suspend fun getOneIssueOfRepo(repoFullName: String, id: Int): Issue = issueDataSource.getOneIssueOfRepo(repoFullName, id)
    suspend fun getCommentsOfIssue(repoFullName: String, id: Int): List<IssueComment> = issueDataSource.getCommentsOfIssue(repoFullName, id)
}