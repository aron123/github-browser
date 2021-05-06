package hu.arondev.uni.mobileprog.githubbrowser

import android.app.Application
import android.util.Log
import hu.arondev.uni.mobileprog.core.data.IssueRepository
import hu.arondev.uni.mobileprog.core.data.RepoRepository
import hu.arondev.uni.mobileprog.core.data.UserRepository
import hu.arondev.uni.mobileprog.core.interactor.*
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.framework.rest.datasource.IssueHttpDataSource
import hu.arondev.uni.mobileprog.framework.rest.datasource.RepoHttpDataSource
import hu.arondev.uni.mobileprog.framework.rest.datasource.UserHttpDataSource

class GitHubBrowserApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val issueRepository = IssueRepository(IssueHttpDataSource(this))
        val repoRepository = RepoRepository(RepoHttpDataSource(this))
        val userRepository = UserRepository(UserHttpDataSource(this))

        GitHubViewModelFactory.inject(
            this,
            Interactors(
                SearchReposByName(repoRepository),
                GetRepositoryByFullName(repoRepository),
                GetReposOfUser(repoRepository),
                GetFilesOfRepo(repoRepository),
                IsRepoStarred(repoRepository),
                StarRepo(repoRepository),
                UnstarRepo(repoRepository),
                SearchUsers(userRepository),
                GetCurrentUser(userRepository),
                GetOneUser(userRepository),
                GetIssuesOfRepo(issueRepository),
                GetOneIssueOfRepo(issueRepository),
                GetCommentsOfIssue(issueRepository),
                CreateIssue(issueRepository),
                CreateIssueComment(issueRepository)
            )
        )
    }
}