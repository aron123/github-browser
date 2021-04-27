package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.User

interface UserDataSource {
    suspend fun searchUsersByUsername(username: String): List<User>
    suspend fun getCurrentUser(authToken: String): User
    suspend fun getOneUserByUsername(username: String): User
}