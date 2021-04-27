package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.IssueComment

interface IssueDataSource {
    suspend fun getIssuesOfRepo(repoFullName: String): List<Issue>
    suspend fun getOneIssueOfRepo(repoFullName: String, id: Int): Issue
    suspend fun getCommentsOfIssue(repoFullName: String, id: Int): List<IssueComment>
}