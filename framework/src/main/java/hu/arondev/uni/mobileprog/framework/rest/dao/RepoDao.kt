package hu.arondev.uni.mobileprog.framework.rest.dao

import hu.arondev.uni.mobileprog.framework.rest.entity.RepoEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.RepoSearchEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoDao {
    @GET("search/repositories")
    suspend fun searchReposByName(@Query("q") repoName: String, @Query("per_page") perPage: Int): RepoSearchEntity

    @GET("repos/{owner}/{repo}")
    suspend fun getRepoByFullName(@Path("owner") owner: String, @Path("repo") repo: String): RepoEntity

    @GET("users/{user}/repos?sort=updated")
    suspend fun getReposOfUser(@Path("user") user: String): List<RepoEntity>
}