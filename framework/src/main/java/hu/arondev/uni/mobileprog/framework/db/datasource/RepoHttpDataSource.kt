package hu.arondev.uni.mobileprog.framework.db.datasource

import android.content.Context
import hu.arondev.uni.mobileprog.core.data.RepoDataSource
import hu.arondev.uni.mobileprog.core.domain.Repo
import hu.arondev.uni.mobileprog.framework.db.GithubApiClient

class RepoHttpDataSource(context: Context): RepoDataSource {
    private val repoDao = GithubApiClient.getInstance(context).repoDao()

    override suspend fun searchReposByName(repoName: String, perPage: Int): List<Repo> {
        TODO("Not yet implemented")
    }

    override suspend fun getOneRepositoryByFullName(fullName: String): Repo {
        TODO("Not yet implemented")
    }
}