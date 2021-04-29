package hu.arondev.uni.mobileprog.framework.db.datasource.converter

import hu.arondev.uni.mobileprog.core.domain.User
import hu.arondev.uni.mobileprog.framework.db.entity.UserEntity
import org.mapstruct.Mapper

@Mapper
interface UserConverter {
    fun convertToDomain(user: UserEntity): User
    fun convertToDomain(users: List<UserEntity>): List<User>
}