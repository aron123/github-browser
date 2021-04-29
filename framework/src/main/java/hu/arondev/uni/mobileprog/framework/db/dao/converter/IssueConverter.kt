package hu.arondev.uni.mobileprog.framework.db.dao.converter

import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.Owner
import hu.arondev.uni.mobileprog.framework.db.entity.IssueEntity
import hu.arondev.uni.mobileprog.framework.db.entity.OwnerEntity
import org.mapstruct.Mapper

@Mapper
interface IssueConverter {
    fun convertToDomain(issue: IssueEntity) : Issue
    fun convertToDomain(issues: List<IssueEntity>): List<Issue>
    fun convertToDomain(owner: OwnerEntity): Owner
}