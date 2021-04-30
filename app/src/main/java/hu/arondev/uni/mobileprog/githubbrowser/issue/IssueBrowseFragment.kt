package hu.arondev.uni.mobileprog.githubbrowser.issue

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.arondev.uni.mobileprog.githubbrowser.R

class IssueBrowseFragment : Fragment() {

    companion object {
        fun newInstance() = IssueBrowseFragment()
    }

    private lateinit var viewModel: IssueBrowseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.issue_browse_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IssueBrowseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}