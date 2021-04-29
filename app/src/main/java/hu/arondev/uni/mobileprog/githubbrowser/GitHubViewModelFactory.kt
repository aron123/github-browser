package hu.arondev.uni.mobileprog.githubbrowser

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.arondev.uni.mobileprog.framework.Interactors

object GitHubViewModelFactory : ViewModelProvider.Factory {

    lateinit var application: Application
    lateinit var dependencies: Interactors

    fun inject(application: Application, dependencies: Interactors) {
        GitHubViewModelFactory.application = application
        GitHubViewModelFactory.dependencies = dependencies
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(GitHubViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java, Interactors::class.java)
                    .newInstance(
                            application,
                            dependencies)
        } else {
            throw IllegalStateException("ViewModel must extend GitHubViewModel")
        }
    }
}