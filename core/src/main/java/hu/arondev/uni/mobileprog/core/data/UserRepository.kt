package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.User

class UserRepository(private val userDataSource: UserDataSource) {
    suspend fun searchUsersByUsername(username: String, perPage: Int = 100): List<User> = userDataSource.searchUsersByUsername(username, perPage)
    suspend fun getCurrentUser(): User = userDataSource.getCurrentUser()
    suspend fun getOneUserByUsername(username: String): User = userDataSource.getOneUserByUsername(username)
}