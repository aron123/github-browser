package hu.arondev.uni.mobileprog.githubbrowser.repo.page

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.MainActivityDelegate
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.repo_page_fragment.*
import retrofit2.HttpException
import java.lang.ClassCastException

class RepoPageFragment : Fragment() {

    companion object {
        enum class ArgumentKeys {
            USERNAME, REPONAME
        }
        fun newInstance() = RepoPageFragment()
        fun newInstance(username: String, repo: String): RepoPageFragment {
            val args = Bundle()
            args.putString(ArgumentKeys.USERNAME.toString(), username)
            args.putString(ArgumentKeys.REPONAME.toString(), repo)

            val fragment = RepoPageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: RepoPageViewModel

    private lateinit var mainActivityDelegate: MainActivityDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            mainActivityDelegate = context as MainActivityDelegate
        } catch (e: ClassCastException) {
            throw ClassCastException()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repo_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(RepoPageViewModel::class.java)

        val username = arguments?.getString(ArgumentKeys.USERNAME.toString())
        val repo = arguments?.getString(ArgumentKeys.REPONAME.toString())

        if (username == null || repo == null) {
            Snackbar.make(view!!, R.string.repo_load_error, Snackbar.LENGTH_LONG)
            return
        }

        viewModel.repo.observe(this, { repo ->
            repo_name.text = repo.name
            repo_owner.text = repo.owner.login
            repo_desc.text = repo.description
            repo_language.text = repo.language
        })

        viewModel.fileList.observe(this, { files ->
            val fileAdapter = FileAdapter(files) { file ->
                if (file.type == "dir") {
                    viewModel.loadRepoFiles(username, repo, file.path)
                }
            }
            repo_page_files_recyclerview.adapter = fileAdapter
        })

        viewModel.loadRepo(username, repo)
        viewModel.loadRepoFiles(username, repo)

        repo_owner.setOnClickListener {
            mainActivityDelegate.openUserPage(username)
        }
    }
}