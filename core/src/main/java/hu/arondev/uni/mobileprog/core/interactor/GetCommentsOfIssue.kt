package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.IssueRepository

class GetCommentsOfIssue(private val issueRepository: IssueRepository) {
    suspend operator fun invoke(repoFullName: String, id: Int) = issueRepository.getCommentsOfIssue(repoFullName, id)
}