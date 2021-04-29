package hu.arondev.uni.mobileprog.framework.db.datasource

import android.content.Context
import hu.arondev.uni.mobileprog.core.data.UserDataSource
import hu.arondev.uni.mobileprog.core.domain.User
import hu.arondev.uni.mobileprog.framework.db.GithubApiClient
import hu.arondev.uni.mobileprog.framework.db.datasource.converter.UserConverter
import org.mapstruct.factory.Mappers

class UserHttpDataSource(context: Context) : UserDataSource {
    private val userDao = GithubApiClient.getInstance(context).userDao()
    private val userConverter = Mappers.getMapper(UserConverter::class.java)

    override suspend fun searchUsersByUsername(username: String): List<User>
        = userConverter.convertToDomain(userDao.searchUsers(username).items)

    override suspend fun getCurrentUser(): User = userConverter.convertToDomain(userDao.getCurrentUser())

    override suspend fun getOneUserByUsername(username: String): User
        = userConverter.convertToDomain(userDao.getOneUser(username))
}