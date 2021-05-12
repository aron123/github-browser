package hu.arondev.uni.mobileprog.githubbrowser.issue

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModel
import kotlinx.coroutines.launch

class AddIssueViewModel(application: Application, interactors: Interactors)
    : GitHubViewModel(application, interactors) {
    val issueAdded: MutableLiveData<Issue> = MutableLiveData()

    fun createIssue(owner: String, repo: String, issue: Issue) {
        viewModelScope.launch {
            issueAdded.postValue(interactors.createIssue(owner, repo, issue))
        }
    }
}