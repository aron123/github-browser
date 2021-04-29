package hu.arondev.uni.mobileprog.framework.rest.datasource

import android.content.Context
import hu.arondev.uni.mobileprog.core.data.IssueDataSource
import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.framework.rest.GithubApiClient
import hu.arondev.uni.mobileprog.framework.rest.datasource.converter.IssueCommentConverter
import hu.arondev.uni.mobileprog.framework.rest.datasource.converter.IssueConverter
import org.mapstruct.factory.Mappers

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
}