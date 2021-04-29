package hu.arondev.uni.mobileprog.framework.db.datasource

import android.content.Context
import hu.arondev.uni.mobileprog.core.data.RepoDataSource
import hu.arondev.uni.mobileprog.core.domain.Repo
import hu.arondev.uni.mobileprog.framework.db.GithubApiClient
import hu.arondev.uni.mobileprog.framework.db.dao.converter.RepoConverter
import org.mapstruct.factory.Mappers

class RepoHttpDataSource(context: Context): RepoDataSource {
    private val repoDao = GithubApiClient.getInstance(context).repoDao()
    private val repoConverter = Mappers.getMapper(RepoConverter::class.java)

    override suspend fun searchReposByName(repoName: String, perPage: Int): List<Repo>
        = repoConverter.convertToDomain(repoDao.searchReposByName(repoName, perPage).items)

    override suspend fun getOneRepositoryByFullName(owner: String, repo: String): Repo
        = repoConverter.convertToDomain(repoDao.getRepoByFullName(owner, repo))
}