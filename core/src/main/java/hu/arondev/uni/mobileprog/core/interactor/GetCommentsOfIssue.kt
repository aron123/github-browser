package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.IssueRepository

class GetCommentsOfIssue(private val issueRepository: IssueRepository) {
    suspend operator fun invoke(owner: String, repo: String, id: Int) = issueRepository.getCommentsOfIssue(owner, repo, id)
}