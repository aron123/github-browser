package hu.arondev.uni.mobileprog.githubbrowser.issue

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.MainActivityDelegate
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.issue_page_fragment.*
import java.lang.ClassCastException
import java.lang.RuntimeException

class IssuePageFragment : Fragment() {

    companion object {
        enum class ArgumentKeys {
            USERNAME, REPONAME, ISSUE_NUMBER
        }
        fun newInstance() = IssuePageFragment()
        fun newInstance(username: String, repo: String, issueNumber: Int): IssuePageFragment {
            val fragment = IssuePageFragment()
            fragment.arguments = bundleOf(
                ArgumentKeys.USERNAME.toString() to username,
                ArgumentKeys.REPONAME.toString() to repo,
                ArgumentKeys.ISSUE_NUMBER.toString() to issueNumber
            )
            return fragment
        }
    }

    private lateinit var viewModel: IssuePageViewModel

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
        return inflater.inflate(R.layout.issue_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(IssuePageViewModel::class.java)

        val username = arguments?.getString(ArgumentKeys.USERNAME.toString())
        val repoName = arguments?.getString(ArgumentKeys.REPONAME.toString())
        val issueNumber = arguments?.getInt(ArgumentKeys.ISSUE_NUMBER.toString())

        if (username == null || repoName == null || issueNumber == null) {
            throw RuntimeException("Can not initialize IssuePageFragment: parameters can not be null")
        }

        issue_page_repo_owner.text = username
        issue_page_repo_name.text = repoName
        issue_page_issue_number.text = resources.getString(R.string.issue_number, issueNumber)

        val issueCommentAdapter = IssueCommentAdapter(context!!)
        issue_page_recyclerview.adapter = issueCommentAdapter

        viewModel.issue.observe(this) { issue ->
            viewModel.loadComments(username, repoName, issue.number)
        }

        viewModel.comments.observe(this) { issueComments ->
            val issue = viewModel.issue.value
            val mutableIssueComments = issueComments.toMutableList()
            mutableIssueComments.add(0, IssueComment().apply {
                user = issue?.user!!
                body = issue.body
                created_at = issue.created_at
            })
            issueCommentAdapter.update(mutableIssueComments)
        }

        viewModel.loadIssue(username, repoName, issueNumber)

        issue_page_repo_name.setOnClickListener {
            mainActivityDelegate.openRepositoryPage(username, repoName)
        }

        issue_page_fab.setOnClickListener {
            mainActivityDelegate.openAddIssueCommentPage(username, repoName, issueNumber)
        }
    }
}