package hu.arondev.uni.mobileprog.githubbrowser.issue

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModel
import kotlinx.coroutines.launch

class IssuePageViewModel(application: Application, interactors: Interactors)
    : GitHubViewModel(application, interactors) {
    val issue: MutableLiveData<Issue> = MutableLiveData()
    val comments: MutableLiveData<List<IssueComment>> = MutableLiveData()

    fun loadIssue(user: String, repo: String, issueNumber: Int) {
        viewModelScope.launch {
            issue.value = interactors.getOneIssueOfRepo(user, repo, issueNumber)
        }
    }

    fun loadComments(user: String, repo: String, issueNumber: Int) {
        viewModelScope.launch {
            comments.value = interactors.getCommentsOfIssue(user, repo, issueNumber)
        }
    }
}