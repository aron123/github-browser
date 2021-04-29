package hu.arondev.uni.mobileprog.framework.db.datasource.converter

import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.core.domain.Owner
import hu.arondev.uni.mobileprog.framework.db.entity.IssueCommentEntity
import hu.arondev.uni.mobileprog.framework.db.entity.OwnerEntity
import org.mapstruct.Mapper

@Mapper
interface IssueCommentConverter {
    fun convertToDomain(issueCommentEntity: IssueCommentEntity): IssueComment
    fun convertToDomain(issueCommentEntity: List<IssueCommentEntity>): List<IssueComment>
    fun convertToDomain(ownerEntity: OwnerEntity): Owner
}