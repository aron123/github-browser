package hu.arondev.uni.mobileprog.framework.rest.dao

import hu.arondev.uni.mobileprog.framework.rest.entity.FileEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.RepoEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.RepoSearchEntity
import retrofit2.Response
import retrofit2.http.*

interface RepoDao {
    @GET("search/repositories")
    suspend fun searchReposByName(@Query("q") repoName: String, @Query("per_page") perPage: Int): RepoSearchEntity

    @GET("repos/{owner}/{repo}")
    suspend fun getRepoByFullName(@Path("owner") owner: String, @Path("repo") repo: String): RepoEntity

    @GET("users/{user}/repos?sort=updated")
    suspend fun getReposOfUser(@Path("user") user: String): List<RepoEntity>

    @GET("repos/{user}/{repo}/contents/{path}")
    suspend fun getFilesOfRepo(@Path("user") user: String, @Path("repo") repo: String,
                               @Path(value = "path", encoded = true) path: String): List<FileEntity>

    @GET("/user/starred/{user}/{repo}")
    suspend fun isRepoStarred(@Path("user") user: String, @Path("repo") repo: String): Response<Unit>

    @PUT("/user/starred/{user}/{repo}")
    suspend fun starRepo(@Path("user") user: String, @Path("repo") repo: String): Response<Unit>

    @DELETE("/user/starred/{user}/{repo}")
    suspend fun unstarRepo(@Path("user") user: String, @Path("repo") repo: String): Response<Unit>
}