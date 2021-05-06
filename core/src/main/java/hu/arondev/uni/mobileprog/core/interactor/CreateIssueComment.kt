package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.IssueRepository
import hu.arondev.uni.mobileprog.core.domain.IssueComment

class CreateIssueComment(private val issueRepository: IssueRepository) {
    suspend operator fun invoke(owner: String, repo: String, issueNumber: Int, issueComment: IssueComment)
        = issueRepository.createIssueComment(owner, repo, issueNumber, issueComment)
}