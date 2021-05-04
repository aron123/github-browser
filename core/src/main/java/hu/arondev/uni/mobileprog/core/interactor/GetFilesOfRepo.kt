package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.RepoRepository

class GetFilesOfRepo(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(user: String, repoName: String, path: String) = repoRepository.getFilesOfRepo(user, repoName, path)
}