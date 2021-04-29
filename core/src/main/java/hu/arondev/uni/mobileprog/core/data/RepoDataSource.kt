package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.Repo

interface RepoDataSource {
    suspend fun searchReposByName(repoName: String, perPage: Int): List<Repo>
    suspend fun getOneRepositoryByFullName(owner: String, repo: String): Repo
}