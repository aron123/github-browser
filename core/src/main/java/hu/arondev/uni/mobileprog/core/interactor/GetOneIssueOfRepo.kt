package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.IssueRepository

class GetOneIssueOfRepo(private val issueRepository: IssueRepository) {
    suspend operator fun invoke(repoFullName: String, id: Int) = issueRepository.getOneIssueOfRepo(repoFullName, id)
}