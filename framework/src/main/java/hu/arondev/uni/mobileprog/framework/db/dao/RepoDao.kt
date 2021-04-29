package hu.arondev.uni.mobileprog.framework.db.dao

import hu.arondev.uni.mobileprog.framework.BuildConfig
import hu.arondev.uni.mobileprog.framework.db.entity.RepoEntity
import hu.arondev.uni.mobileprog.framework.db.entity.RepoSearchEntity
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoDao {
    @GET("search/repositories")
    @Headers("Authorization: Basic " + BuildConfig.AUTH_TOKEN)
    suspend fun searchReposByName(@Query("q") repoName: String, @Query("per_page") perPage: Int): RepoSearchEntity

    @GET("repos/{owner}/{repo}")
    @Headers("Authorization: Basic " + BuildConfig.AUTH_TOKEN)
    suspend fun getRepoByFullName(@Path("owner") owner: String, @Path("repo") repo: String): RepoEntity
}