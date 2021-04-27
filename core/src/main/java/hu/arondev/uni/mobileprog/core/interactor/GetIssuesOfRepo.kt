package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.IssueRepository

class GetIssuesOfRepo(private val issueRepository: IssueRepository) {
    suspend operator fun invoke(repoFullName: String) = issueRepository.getIssuesOfRepo(repoFullName)
}