package hu.arondev.uni.mobileprog.githubbrowser.repo.page

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.Repo
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModel
import kotlinx.coroutines.launch

class RepoPageViewModel(application: Application, interactors: Interactors)
    : GitHubViewModel(application, interactors) {
    val repo: MutableLiveData<Repo> = MutableLiveData()

    fun loadRepo(username: String, repoName: String) {
        viewModelScope.launch {
            repo.value = interactors.getOneRepositoryByFullName(username, repoName)
        }
    }
}