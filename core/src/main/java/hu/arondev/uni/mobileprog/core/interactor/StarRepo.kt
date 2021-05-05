package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.RepoRepository

class StarRepo(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(user: String, repo: String) = repoRepository.starRepo(user, repo)
}