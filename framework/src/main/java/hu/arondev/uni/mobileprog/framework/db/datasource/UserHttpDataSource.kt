package hu.arondev.uni.mobileprog.framework.db.datasource

import android.content.Context
import hu.arondev.uni.mobileprog.core.data.UserDataSource
import hu.arondev.uni.mobileprog.core.domain.User
import hu.arondev.uni.mobileprog.framework.db.GithubApiClient

class UserHttpDataSource(context: Context) : UserDataSource {
    private val userDao = GithubApiClient.getInstance(context).userDao()

    override suspend fun searchUsersByUsername(username: String): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(authToken: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun getOneUserByUsername(username: String): User {
        TODO("Not yet implemented")
    }
}