package hu.arondev.uni.mobileprog.githubbrowser.repo.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.Repo
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModel
import kotlinx.coroutines.launch

class RepoSearchViewModel(application: Application, interactors: Interactors)
    : GitHubViewModel(application, interactors) {
        val repos: MutableLiveData<List<Repo>> = MutableLiveData()

    fun loadRepos(query: String) {
        viewModelScope.launch {
            repos.postValue(interactors.searchReposByName(query, 100))
        }
    }
}