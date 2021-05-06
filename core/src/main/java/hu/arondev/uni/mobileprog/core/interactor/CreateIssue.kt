package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.IssueRepository
import hu.arondev.uni.mobileprog.core.domain.Issue

class CreateIssue(private val issueRepository: IssueRepository) {
    suspend operator fun invoke(owner: String, repo: String, issue: Issue): Issue
            = issueRepository.createIssue(owner, repo, issue)
}