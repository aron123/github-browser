package hu.arondev.uni.mobileprog.framework.rest.dao

import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueCommentCreateEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueCommentEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueCreateEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IssueDao {

    @GET("repos/{owner}/{repo}/issues?state=all&sort=created&direction=desc")
    suspend fun getIssuesOfRepo(@Path("owner") owner: String, @Path("repo") repo: String): List<IssueEntity>

    @GET("repos/{owner}/{repo}/issues/{id}")
    suspend fun getOneIssueOfRepo(@Path("owner") owner: String, @Path("repo") repo: String, @Path("id") id: Int): IssueEntity

    @GET("repos/{owner}/{repo}/issues/{id}/comments")
    suspend fun getCommentsOfIssue(@Path("owner") owner: String, @Path("repo") repo: String, @Path("id") id: Int): List<IssueCommentEntity>

    @POST("/repos/{owner}/{repo}/issues")
    suspend fun createIssue(@Path("owner") owner: String, @Path("repo") repo: String, @Body issue: IssueCreateEntity): IssueEntity

    @POST("/repos/{owner}/{repo}/issues/{issue_number}/comments")
    suspend fun createIssueComment(@Path("owner") owner: String, @Path("repo") repo: String,
                                   @Path("issue_number") issueNumber: Int, @Body issue: IssueCommentCreateEntity): Response<Unit>
}