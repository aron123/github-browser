package hu.arondev.uni.mobileprog.framework.rest.dao

import hu.arondev.uni.mobileprog.framework.rest.entity.IssueCommentEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface IssueDao {

    @GET("repos/{owner}/{repo}/issues?state=all")
    suspend fun getIssuesOfRepo(@Path("owner") owner: String, @Path("repo") repo: String): List<IssueEntity>

    @GET("repos/{owner}/{repo}/issues/{id}")
    suspend fun getOneIssueOfRepo(@Path("owner") owner: String, @Path("repo") repo: String, @Path("id") id: Int): IssueEntity

    @GET("repos/{owner}/{repo}/issues/{id}/comments")
    suspend fun getCommentsOfIssue(@Path("owner") owner: String, @Path("repo") repo: String, @Path("id") id: Int): List<IssueCommentEntity>
}