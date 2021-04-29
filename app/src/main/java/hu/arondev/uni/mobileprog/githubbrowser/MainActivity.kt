package hu.arondev.uni.mobileprog.githubbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hu.arondev.uni.mobileprog.core.data.IssueRepository
import hu.arondev.uni.mobileprog.core.data.RepoRepository
import hu.arondev.uni.mobileprog.core.data.UserRepository
import hu.arondev.uni.mobileprog.framework.BuildConfig.AUTH_TOKEN
import hu.arondev.uni.mobileprog.framework.db.datasource.IssueHttpDataSource
import hu.arondev.uni.mobileprog.framework.db.datasource.RepoHttpDataSource
import hu.arondev.uni.mobileprog.framework.db.datasource.UserHttpDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: remove FROM
        val issueRepository = IssueRepository(IssueHttpDataSource(this))
        val repoRepository = RepoRepository(RepoHttpDataSource(this))
        val userRepository = UserRepository(UserHttpDataSource(this))
        val id = "CONTENT_LOADED"
        val user = "aron123"
        val repo = "node-barion"

        GlobalScope.launch {

            try {
                val issuesOfRepository = issueRepository.getIssuesOfRepo(user, repo)
                Log.d(id, issuesOfRepository.map{ it.title }.toString())

                val oneIssueOfRepo = issueRepository.getOneIssueOfRepo(user, repo, 5)
                Log.d(id, oneIssueOfRepo.title)

                val commentsOfIssue = issueRepository.getCommentsOfIssue(user, repo, 29)
                Log.d(id, commentsOfIssue.map{ it.user.login }.toString())

                val reposByName = repoRepository.searchReposByName("jquery")
                Log.d(id, reposByName.map{ it.name }.toString())

                val repoByFullName = repoRepository.getOneRepositoryByFullName(user, repo)
                Log.d(id, repoByFullName.html_url)

                val usersByUserName = userRepository.searchUsersByUsername("aron") // TODO: perpage
                Log.d(id, usersByUserName.map { it.login }.take(20).toString())

                val currentUser = userRepository.getCurrentUser();
                Log.d(id, currentUser.email.toString())

                val oneUserByUserName = userRepository.getOneUserByUsername(user)
                Log.d(id, oneUserByUserName.login)
            } catch (ex: HttpException) {
                Log.e(id, ex.response().toString())
            } catch (ex: Exception) {
                Log.e(id, ex.stackTraceToString())
            }
        }
        // TODO: remove TO
    }
}