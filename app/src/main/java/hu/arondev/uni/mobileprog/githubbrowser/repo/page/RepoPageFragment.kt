package hu.arondev.uni.mobileprog.githubbrowser.repo.page

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.MainActivityDelegate
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.repo_page_fragment.*
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
        val repoName = arguments?.getString(ArgumentKeys.REPONAME.toString())

        if (username == null || repoName == null) {
            Snackbar.make(view!!, R.string.repo_load_error, Snackbar.LENGTH_LONG)
            return
        }

        viewModel.repo.observe(this, { repo ->
            repo_name.text = repo.name
            repo_owner.text = repo.owner.login
            repo_desc.text = repo.description
            repo_language.text = repo.language
            repo_open_issues.text = resources.getQuantityString(
                R.plurals.repo_page_open_issues,
                repo.open_issues_count, repo.open_issues_count
            )
            repo_forks.text = resources.getQuantityString(
                R.plurals.repo_page_forks,
                repo.forks_count, repo.forks_count
            )
        })

        viewModel.fileList.observe(this, { files ->
            val fileAdapter = FileAdapter(files) { file ->
                if (file.type == "dir") {
                    viewModel.loadRepoFiles(username, repoName, file.path)
                }
            }
            repo_page_files_recyclerview.adapter = fileAdapter
        })

        viewModel.isRepoStarred.observe(this) { starred ->
            repo_page_follow_ic.setImageResource(
                    if (starred) R.drawable.ic_star_filled else R.drawable.ic_star_outline
            )
        }

        viewModel.loadRepo(username, repoName)
        viewModel.loadRepoFiles(username, repoName)
        viewModel.loadIsRepoStarred(username, repoName)

        repo_owner.setOnClickListener { mainActivityDelegate.openUserPage(username) }
        repo_page_follow_ic.setOnClickListener {
            if (viewModel.isRepoStarred.value == true) {
                viewModel.unstarRepo(username, repoName)
            } else {
                viewModel.starRepo(username, repoName)
            }
        }
        repo_open_issues.setOnClickListener { mainActivityDelegate.openIssueBrowsePage(username, repoName) }
    }
}