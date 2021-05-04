package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.Repo

class RepoRepository(private val repoDataSource: RepoDataSource) {
    suspend fun searchReposByName(repoName: String, perPage: Int = 20): List<Repo> = repoDataSource.searchReposByName(repoName, perPage)
    suspend fun getOneRepositoryByFullName(owner: String, repo: String): Repo = repoDataSource.getOneRepositoryByFullName(owner, repo)
    suspend fun getReposOfUser(username: String): List<Repo> = repoDataSource.getReposOfUser(username)
}