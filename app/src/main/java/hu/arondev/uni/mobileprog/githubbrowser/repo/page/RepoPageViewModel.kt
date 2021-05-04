package hu.arondev.uni.mobileprog.githubbrowser.repo.page

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.arondev.uni.mobileprog.core.domain.File
import hu.arondev.uni.mobileprog.core.domain.Repo
import hu.arondev.uni.mobileprog.framework.Interactors
import hu.arondev.uni.mobileprog.framework.util.StringUtil
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RepoPageViewModel(application: Application, interactors: Interactors)
    : GitHubViewModel(application, interactors) {
    val repo: MutableLiveData<Repo> = MutableLiveData()
    val fileList: MutableLiveData<List<File>> = MutableLiveData()

    fun loadRepo(username: String, repoName: String) {
        viewModelScope.launch {
            repo.value = interactors.getOneRepositoryByFullName(username, repoName)
        }
    }

    fun loadRepoFiles(username: String, repoName: String, path: String = "") {
        viewModelScope.launch {
            val files: MutableList<File>
            try {
                files = interactors.getFilesOfRepo(username, repoName, path).toMutableList()
                if (path != "" &&  path != "/") {
                    files.add(0, File().apply {
                        name = ".."
                        size = 0
                        type = "dir"
                        this.path = StringUtil.getParentPath(path)
                    })
                }
                fileList.value = files
            } catch (ex: HttpException) {
                Log.e("REPO_PAGE_VIEWMODEL", ex.response().toString())
            }
        }
    }
}