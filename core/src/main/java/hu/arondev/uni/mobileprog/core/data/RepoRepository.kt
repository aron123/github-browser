package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.Repo

class RepoRepository(private val repoDataSource: RepoDataSource) {
    suspend fun searchReposByName(repoName: String, perPage: Int): List<Repo> = repoDataSource.searchReposByName(repoName, perPage)
    suspend fun getOneRepositoryByFullName(fullName: String): Repo = repoDataSource.getOneRepositoryByFullName(fullName)
}