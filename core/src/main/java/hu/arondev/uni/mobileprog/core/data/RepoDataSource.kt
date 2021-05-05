package hu.arondev.uni.mobileprog.core.data

import hu.arondev.uni.mobileprog.core.domain.File
import hu.arondev.uni.mobileprog.core.domain.Repo
import javax.xml.ws.Response

interface RepoDataSource {
    suspend fun searchReposByName(repoName: String, perPage: Int): List<Repo>
    suspend fun getOneRepositoryByFullName(owner: String, repo: String): Repo
    suspend fun getReposOfUser(user: String): List<Repo>
    suspend fun getFilesOfRepo(user: String, repoName: String, path: String): List<File>
    suspend fun isRepoStarred(user: String, repo:String): Boolean
    suspend fun starRepo(user: String, repo: String)
    suspend fun unstarRepo(user: String, repo: String)
}