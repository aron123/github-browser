package hu.arondev.uni.mobileprog.githubbrowser.repo.page

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.arondev.uni.mobileprog.githubbrowser.R

class RepoPageFragment : Fragment() {

    companion object {
        fun newInstance() = RepoPageFragment()
    }

    private lateinit var viewModel: RepoPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repo_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepoPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}