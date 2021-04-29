package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.IssueComment

class IssueRepository(private val issueDataSource: IssueDataSource) {
    suspend fun getIssuesOfRepo(owner: String, repo: String): List<Issue> = issueDataSource.getIssuesOfRepo(owner, repo)
    suspend fun getOneIssueOfRepo(owner: String, repo: String, id: Int): Issue = issueDataSource.getOneIssueOfRepo(owner, repo, id)
    suspend fun getCommentsOfIssue(owner: String, repo: String, id: Int): List<IssueComment> = issueDataSource.getCommentsOfIssue(owner, repo, id)
}