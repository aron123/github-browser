package hu.arondev.uni.mobileprog.githubbrowser.issue

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.arondev.uni.mobileprog.githubbrowser.R

class AddIssueCommentFragment : Fragment() {

    companion object {
        fun newInstance() = AddIssueCommentFragment()
    }

    private lateinit var viewModel: AddIssueCommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_issue_comment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddIssueCommentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}