package hu.arondev.uni.mobileprog.githubbrowser.user.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.User
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModel
import kotlinx.coroutines.launch

class UserSearchViewModel(application: Application, interactors: Interactors)
    : GitHubViewModel(application, interactors) {

    val users: MutableLiveData<List<User>> = MutableLiveData()

    fun loadUsers(search: String) {
        viewModelScope.launch {
            users.value = interactors.searchUsers(search)
        }
    }

}