package hu.arondev.uni.mobileprog.githubbrowser.issue

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.MainActivityDelegate
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.issue_browse_fragment.*
import java.lang.ClassCastException
import java.lang.RuntimeException

class IssueBrowseFragment : Fragment() {

    companion object {
        enum class ArgumentKeys {
            USERNAME, REPONAME
        }
        fun newInstance() = IssueBrowseFragment()
        fun newInstance(username: String, repo: String): IssueBrowseFragment {
            val fragment = IssueBrowseFragment()
            fragment.arguments = bundleOf(
                ArgumentKeys.USERNAME.toString() to username,
                ArgumentKeys.REPONAME.toString() to repo
            )
            return fragment
        }
    }

    private lateinit var viewModel: IssueBrowseViewModel

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
        return inflater.inflate(R.layout.issue_browse_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(IssueBrowseViewModel::class.java)

        val username = arguments?.getString(ArgumentKeys.USERNAME.toString())
        val repoName = arguments?.getString(ArgumentKeys.REPONAME.toString())

        if (username == null || repoName == null) {
            throw RuntimeException("Issue initialization failed: username and reponame can not be null")
        }

        issue_browse_repo_owner.text = username
        issue_browse_repo_name.text = repoName
        issue_browse_repo_name.setOnClickListener { mainActivityDelegate.openRepositoryPage(username, repoName) }

        val issueAdapter = IssueAdapter(context!!) { issue ->
            mainActivityDelegate.openIssuePage(username, repoName, issue.number)
        }
        issue_browse_recyclerview.adapter = issueAdapter

        viewModel.issues.observe(this) { issues -> issueAdapter.update(issues) }

        viewModel.loadIssues(username, repoName)

        issue_browse_fab.setOnClickListener {
            mainActivityDelegate.openAddIssuePage(username, repoName)
        }
    }

}