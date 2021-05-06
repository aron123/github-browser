package hu.arondev.uni.mobileprog.framework.rest.datasource

import android.content.Context
import hu.arondev.uni.mobileprog.core.data.IssueDataSource
import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.framework.rest.GithubApiClient
import hu.arondev.uni.mobileprog.framework.rest.datasource.converter.IssueCommentConverter
import hu.arondev.uni.mobileprog.framework.rest.datasource.converter.IssueConverter
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueCreateEntity
import org.mapstruct.factory.Mappers
import java.lang.RuntimeException

class IssueHttpDataSource(context: Context) : IssueDataSource {
    private val issueDao = GithubApiClient.getInstance(context).issueDao()
    private val issueConverter = Mappers.getMapper(IssueConverter::class.java)
    private val issueCommentConverter = Mappers.getMapper(IssueCommentConverter::class.java)

    override suspend fun getIssuesOfRepo(owner: String, repo: String): List<Issue>
        = issueConverter.convertToDomain(issueDao.getIssuesOfRepo(owner, repo))

    override suspend fun getOneIssueOfRepo(owner: String, repo: String, id: Int): Issue
        = issueConverter.convertToDomain(issueDao.getOneIssueOfRepo(owner, repo, id))

    override suspend fun getCommentsOfIssue(owner: String, repo: String, id: Int): List<IssueComment>
        = issueCommentConverter.convertToDomain(issueDao.getCommentsOfIssue(owner, repo, id))

    override suspend fun createIssue(owner: String, repo: String, issue: Issue): Issue {
        val body: IssueCreateEntity = issueConverter.convertToCreateEntity(issue)
        val response = issueDao.createIssue(owner, repo, body)
        return issueConverter.convertToDomain(response)
    }

    override suspend fun createIssueComment(
        owner: String,repo: String,
        issueNumber: Int, issueComment: IssueComment
    ) {
        val body = issueCommentConverter.convertToCreateEntity(issueComment)
        val response = issueDao.createIssueComment(owner, repo, issueNumber, body)
        if (!response.isSuccessful) {
            throw RuntimeException("Creating issue comment failed (code: ${response.code()})")
        }
    }
}