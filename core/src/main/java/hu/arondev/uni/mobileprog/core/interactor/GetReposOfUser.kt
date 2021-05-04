package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.RepoRepository

class GetReposOfUser(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(username: String) = repoRepository.getReposOfUser(username)
}