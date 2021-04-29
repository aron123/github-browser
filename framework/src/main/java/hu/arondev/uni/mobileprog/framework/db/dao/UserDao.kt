package hu.arondev.uni.mobileprog.framework.db.dao

import hu.arondev.uni.mobileprog.framework.BuildConfig
import hu.arondev.uni.mobileprog.framework.db.entity.UserEntity
import hu.arondev.uni.mobileprog.framework.db.entity.UserSearchEntity
import retrofit2.http.*

interface UserDao {
    @GET("search/users")
    @Headers("Authorization: Basic " + BuildConfig.AUTH_TOKEN)
    suspend fun searchUsers(@Query("q") username: String): UserSearchEntity

    @GET("users/{username}")
    @Headers("Authorization: Basic " + BuildConfig.AUTH_TOKEN)
    suspend fun getOneUser(@Path("username") username: String): UserEntity

    @GET("user")
    @Headers("Authorization: Basic " + BuildConfig.AUTH_TOKEN)
    suspend fun getCurrentUser(): UserEntity
}