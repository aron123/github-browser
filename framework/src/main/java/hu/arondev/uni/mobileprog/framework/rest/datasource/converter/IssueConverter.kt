package hu.arondev.uni.mobileprog.framework.rest.datasource.converter

import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.Owner
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueCreateEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.OwnerEntity
import org.mapstruct.Mapper

@Mapper
interface IssueConverter {
    fun convertToDomain(issue: IssueEntity) : Issue
    fun convertToDomain(issues: List<IssueEntity>): List<Issue>
    fun convertToDomain(owner: OwnerEntity): Owner
    fun convertToCreateEntity(issue: Issue): IssueCreateEntity
}