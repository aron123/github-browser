package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.RepoRepository

class GetRepositoryByFullName(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(owner: String, repo: String) = repoRepository.getOneRepositoryByFullName(owner, repo)
}