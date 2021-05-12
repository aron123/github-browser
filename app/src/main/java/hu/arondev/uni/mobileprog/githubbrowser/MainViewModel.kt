package hu.arondev.uni.mobileprog.githubbrowser

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.User
import hu.arondev.uni.mobileprog.framework.Interactors
import kotlinx.coroutines.launch

class MainViewModel(application: Application, interactors: Interactors)
    : GitHubViewModel(application, interactors) {

    val currentUser = MutableLiveData<User>()

    fun getCurrentUser() {
        viewModelScope.launch {
            val user = interactors.getCurrentUser()
            currentUser.postValue(user)
        }
    }
}