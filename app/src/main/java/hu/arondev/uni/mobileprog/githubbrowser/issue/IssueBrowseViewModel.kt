package hu.arondev.uni.mobileprog.githubbrowser.issue

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModel
import kotlinx.coroutines.launch

class IssueBrowseViewModel(application: Application, interactors: Interactors)
    : GitHubViewModel(application, interactors) {
    val issues: MutableLiveData<List<Issue>> = MutableLiveData()

    fun loadIssues(user: String, repo: String) {
        viewModelScope.launch {
            issues.value = interactors.getIssuesOfRepo(user, repo)
        }
    }
}