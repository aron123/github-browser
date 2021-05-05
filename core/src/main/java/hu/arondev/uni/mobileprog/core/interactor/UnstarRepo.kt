package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.RepoRepository

class UnstarRepo(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(user: String, repo: String) = repoRepository.unstarRepo(user, repo)
}