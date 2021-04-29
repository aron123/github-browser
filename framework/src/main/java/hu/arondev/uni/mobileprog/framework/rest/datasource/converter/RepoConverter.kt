package hu.arondev.uni.mobileprog.framework.rest.datasource.converter

import hu.arondev.uni.mobileprog.core.domain.Owner
import hu.arondev.uni.mobileprog.core.domain.Repo
import hu.arondev.uni.mobileprog.framework.rest.entity.OwnerEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.RepoEntity
import org.mapstruct.Mapper

@Mapper
interface RepoConverter {
    fun convertToDomain(user: RepoEntity): Repo
    fun convertToDomain(users: List<RepoEntity>): List<Repo>
    fun convertToDomain(owner: OwnerEntity): Owner
}