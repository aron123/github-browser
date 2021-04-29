package hu.arondev.uni.mobileprog.githubbrowser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import hu.arondev.uni.mobileprog.framework.Interactors

open class GitHubViewModel(application: Application, protected val interactors: Interactors) :
        AndroidViewModel(application) {

    protected val application: GitHubBrowserApplication = getApplication()

}