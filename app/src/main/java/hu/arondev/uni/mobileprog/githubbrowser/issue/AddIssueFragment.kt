package hu.arondev.uni.mobileprog.githubbrowser.issue

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.snackbar.Snackbar
import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.MainActivityDelegate
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.add_issue_fragment.*
import java.lang.ClassCastException
import java.lang.RuntimeException

class AddIssueFragment : Fragment() {

    companion object {
        enum class ArgumentKeys {
            USERNAME, REPONAME
        }
        fun newInstance() = AddIssueFragment()
        fun newInstance(username: String, repo: String): AddIssueFragment {
            val fragment = AddIssueFragment()
            fragment.arguments = bundleOf(
                ArgumentKeys.USERNAME.toString() to username,
                ArgumentKeys.REPONAME.toString() to repo
            )
            return fragment
        }
    }

    private lateinit var viewModel: AddIssueViewModel

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
        return inflater.inflate(R.layout.add_issue_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(AddIssueViewModel::class.java)

        val username = arguments?.getString(IssuePageFragment.Companion.ArgumentKeys.USERNAME.toString())
        val repoName = arguments?.getString(IssuePageFragment.Companion.ArgumentKeys.REPONAME.toString())

        if (username == null || repoName == null) {
            throw RuntimeException("Can not initialize AddIssueFragment: parameters can not be null")
        }

        add_issue_repo_owner.text = username
        add_issue_repo_name.text = repoName

        viewModel.issueAdded.observe(this) { issue ->
            mainActivityDelegate.hideKeyboard()
            mainActivityDelegate.openIssuePage(username, repoName, issue.number)
        }

        add_issue_repo_name.setOnClickListener {
            mainActivityDelegate.openRepositoryPage(username, repoName)
        }

        add_issue_button.setOnClickListener {
            val issue: Issue = Issue().apply {
                title = add_issue_title_input.text.toString()
                body = add_issue_body_input.text.toString()
            }
            viewModel.createIssue(username, repoName, issue)
        }
    }

}