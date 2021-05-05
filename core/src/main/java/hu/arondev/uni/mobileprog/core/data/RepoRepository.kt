package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.File
import hu.arondev.uni.mobileprog.core.domain.Repo

class RepoRepository(private val repoDataSource: RepoDataSource) {
    suspend fun searchReposByName(repoName: String, perPage: Int = 20): List<Repo> = repoDataSource.searchReposByName(repoName, perPage)
    suspend fun getOneRepositoryByFullName(owner: String, repo: String): Repo = repoDataSource.getOneRepositoryByFullName(owner, repo)
    suspend fun getReposOfUser(username: String): List<Repo> = repoDataSource.getReposOfUser(username)
    suspend fun getFilesOfRepo(user: String, repoName: String, path: String): List<File> = repoDataSource.getFilesOfRepo(user, repoName, path)
    suspend fun isRepoStarred(user: String, repo: String): Boolean = repoDataSource.isRepoStarred(user, repo)
    suspend fun starRepo(user: String, repo: String) = repoDataSource.starRepo(user, repo)
    suspend fun unstarRepo(user: String, repo: String) = repoDataSource.unstarRepo(user, repo)
}