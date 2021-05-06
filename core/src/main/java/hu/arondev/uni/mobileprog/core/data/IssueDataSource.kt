package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.IssueComment

interface IssueDataSource {
    suspend fun getIssuesOfRepo(owner: String, repo: String): List<Issue>
    suspend fun getOneIssueOfRepo(owner: String, repo: String, id: Int): Issue
    suspend fun getCommentsOfIssue(owner: String, repo: String, id: Int): List<IssueComment>
    suspend fun createIssue(owner: String, repo: String, issue: Issue): Issue
    suspend fun createIssueComment(owner: String, repo: String, issueNumber: Int, issueComment: IssueComment)
}