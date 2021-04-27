package hu.arondev.uni.mobileprog.framework.db

import android.content.Context
import hu.arondev.uni.mobileprog.framework.db.dao.IssueDao
import hu.arondev.uni.mobileprog.framework.db.dao.RepoDao
import hu.arondev.uni.mobileprog.framework.db.dao.UserDao
import okhttp3.OkHttpClient

class GithubApiClient (private val client: OkHttpClient) {
    companion object {
        private var instance: GithubApiClient? = null

        private fun create (context: Context): GithubApiClient = GithubApiClient(OkHttpClient())

        fun getInstance(context: Context): GithubApiClient {
            return instance ?: create(context).also{ instance = it }
        }
    }

    fun userDao() = UserDao(client)
    fun repoDao() = RepoDao(client)
    fun issueDao() = IssueDao(client)
}