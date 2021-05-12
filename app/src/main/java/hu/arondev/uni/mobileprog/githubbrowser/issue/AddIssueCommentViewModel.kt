package hu.arondev.uni.mobileprog.githubbrowser.issue

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModel
import kotlinx.coroutines.launch

class AddIssueCommentViewModel(application: Application, interactors: Interactors)
    : GitHubViewModel(application, interactors) {

    val issueCommentAdded: MutableLiveData<Boolean> = MutableLiveData(false)

    fun addIssueComment(owner: String, repo: String, issueNumber: Int, issueComment: IssueComment) {
        viewModelScope.launch {
            interactors.createIssueComment(owner, repo, issueNumber, issueComment)
            issueCommentAdded.postValue(true)
        }
    }
}