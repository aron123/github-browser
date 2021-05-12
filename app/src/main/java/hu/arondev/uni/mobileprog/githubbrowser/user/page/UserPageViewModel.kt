package hu.arondev.uni.mobileprog.githubbrowser.user.page

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.Repo
import hu.arondev.uni.mobileprog.core.domain.User
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModel
import kotlinx.coroutines.launch

class UserPageViewModel(application: Application, interactors: Interactors) : GitHubViewModel
    (application, interactors) {
    val user: MutableLiveData<User> = MutableLiveData()
    val repositories: MutableLiveData<List<Repo>> = MutableLiveData()

    fun loadUser(username: String?) {
        viewModelScope.launch {
            user.postValue(
                if (username != null) interactors.getOneUser(username) else interactors.getCurrentUser()
            )
        }
    }

    fun loadRepositoriesOfUser(username: String) {
        viewModelScope.launch {
            repositories.postValue(interactors.getReposOfUser(username))
        }
    }
}