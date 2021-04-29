package hu.arondev.uni.mobileprog.framework.db.datasource.converter

import hu.arondev.uni.mobileprog.core.domain.Owner
import hu.arondev.uni.mobileprog.core.domain.Repo
import hu.arondev.uni.mobileprog.framework.db.entity.OwnerEntity
import hu.arondev.uni.mobileprog.framework.db.entity.RepoEntity
import org.mapstruct.Mapper

@Mapper
interface RepoConverter {
    fun convertToDomain(user: RepoEntity): Repo
    fun convertToDomain(users: List<RepoEntity>): List<Repo>
    fun convertToDomain(owner: OwnerEntity): Owner
}