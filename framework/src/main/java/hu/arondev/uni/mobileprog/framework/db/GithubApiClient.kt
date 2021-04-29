package hu.arondev.uni.mobileprog.framework.db

import android.content.Context
import hu.arondev.uni.mobileprog.framework.BuildConfig
import hu.arondev.uni.mobileprog.framework.db.dao.IssueDao
import hu.arondev.uni.mobileprog.framework.db.dao.RepoDao
import hu.arondev.uni.mobileprog.framework.db.dao.UserDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubApiClient (private val client: Retrofit) {
    companion object {
        private var instance: GithubApiClient? = null

        private fun create (context: Context): GithubApiClient = GithubApiClient(
            Retrofit.Builder()
                .client(AuthClient.getInstance(BuildConfig.AUTH_TOKEN))
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        )

        fun getInstance(context: Context): GithubApiClient {
            return instance ?: create(context).also{ instance = it }
        }
    }

    fun userDao() = client.create(UserDao::class.java)
    fun repoDao() = client.create(RepoDao::class.java)
    fun issueDao() = client.create(IssueDao::class.java)
}