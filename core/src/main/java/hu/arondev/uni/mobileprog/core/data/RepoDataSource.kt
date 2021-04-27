package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.Repo

interface RepoDataSource {
    suspend fun searchReposByName(repoName: String, perPage: Int = 20): List<Repo>
    suspend fun getOneRepositoryByFullName(fullName: String): Repo
}