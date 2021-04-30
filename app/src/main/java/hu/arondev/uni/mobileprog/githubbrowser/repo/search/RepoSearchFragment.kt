package hu.arondev.uni.mobileprog.githubbrowser.repo.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.arondev.uni.mobileprog.githubbrowser.R

class RepoSearchFragment : Fragment() {

    companion object {
        fun newInstance() = RepoSearchFragment()
    }

    private lateinit var viewModel: RepoSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repo_search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepoSearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}