package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.RepoRepository

class SearchReposByName(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(repoName: String, perPage: Int = 20) = repoRepository.searchReposByName(repoName, perPage)
}