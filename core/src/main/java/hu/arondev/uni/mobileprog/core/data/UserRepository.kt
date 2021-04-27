package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.User

class UserRepository(private val userDataSource: UserDataSource) {
    suspend fun searchUsersByUsername(username: String): List<User> = userDataSource.searchUsersByUsername(username)
    suspend fun getCurrentUser(authToken: String): User = userDataSource.getCurrentUser(authToken)
    suspend fun getOneUserByUsername(username: String): User = userDataSource.getOneUserByUsername(username)
}