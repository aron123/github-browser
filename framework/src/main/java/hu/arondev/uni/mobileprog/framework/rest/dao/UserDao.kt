package hu.arondev.uni.mobileprog.framework.rest.dao

import hu.arondev.uni.mobileprog.framework.rest.entity.UserEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.UserSearchEntity
import retrofit2.http.*

interface UserDao {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") username: String): UserSearchEntity

    @GET("users/{username}")
    suspend fun getOneUser(@Path("username") username: String): UserEntity

    @GET("user")
    suspend fun getCurrentUser(): UserEntity
}