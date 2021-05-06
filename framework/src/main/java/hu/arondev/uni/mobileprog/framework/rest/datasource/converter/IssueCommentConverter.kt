package hu.arondev.uni.mobileprog.framework.rest.datasource.converter

import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.core.domain.Owner
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueCommentCreateEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.IssueCommentEntity
import hu.arondev.uni.mobileprog.framework.rest.entity.OwnerEntity
import org.mapstruct.Mapper

@Mapper
interface IssueCommentConverter {
    fun convertToDomain(issueCommentEntity: IssueCommentEntity): IssueComment
    fun convertToDomain(issueCommentEntity: List<IssueCommentEntity>): List<IssueComment>
    fun convertToDomain(ownerEntity: OwnerEntity): Owner
    fun convertToCreateEntity(issueComment: IssueComment): IssueCommentCreateEntity
}