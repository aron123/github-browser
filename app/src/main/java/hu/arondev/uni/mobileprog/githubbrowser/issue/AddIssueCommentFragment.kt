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
import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.MainActivityDelegate
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.add_issue_comment_fragment.*
import java.lang.ClassCastException
import java.lang.RuntimeException

class AddIssueCommentFragment : Fragment() {

    companion object {
        enum class ArgumentKeys {
            USERNAME, REPONAME, ISSUE_NUMBER
        }
        fun newInstance() = AddIssueCommentFragment()
        fun newInstance(username: String, repo: String, issueNumber: Int): AddIssueCommentFragment {
            val fragment = AddIssueCommentFragment()
            fragment.arguments = bundleOf(
                ArgumentKeys.USERNAME.toString() to username,
                ArgumentKeys.REPONAME.toString() to repo,
                ArgumentKeys.ISSUE_NUMBER.toString() to issueNumber
            )
            return fragment
        }
    }

    private lateinit var viewModel: AddIssueCommentViewModel

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
        return inflater.inflate(R.layout.add_issue_comment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(AddIssueCommentViewModel::class.java)

        val username = arguments?.getString(IssuePageFragment.Companion.ArgumentKeys.USERNAME.toString())
        val repoName = arguments?.getString(IssuePageFragment.Companion.ArgumentKeys.REPONAME.toString())
        val issueNumber = arguments?.getInt(IssuePageFragment.Companion.ArgumentKeys.ISSUE_NUMBER.toString())

        if (username == null || repoName == null || issueNumber == null) {
            throw RuntimeException("Can not initialize AddIssueCommentFragment: parameters can not be null")
        }

        add_issue_comment_repo_owner.text = username
        add_issue_comment_repo_name.text = repoName
        add_issue_comment_issue_number.text = resources.getString(R.string.issue_number, issueNumber)

        viewModel.issueCommentAdded.observe(this) { added ->
            if (added) {
                mainActivityDelegate.hideKeyboard()
                mainActivityDelegate.openIssuePage(username, repoName, issueNumber)
            } else {
                Snackbar.make(view!!, "Unable to add issue comment", Snackbar.LENGTH_LONG)
            }
        }

        add_issue_comment_button.setOnClickListener {
            val issueComment = IssueComment().apply {
                body = add_issue_comment_body_input.text.toString()
            }
            viewModel.addIssueComment(username, repoName, issueNumber, issueComment)
        }

        add_issue_comment_issue_number.setOnClickListener {
            mainActivityDelegate.openIssuePage(username, repoName, issueNumber)
        }
    }

}