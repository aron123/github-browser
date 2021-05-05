package hu.arondev.uni.mobileprog.githubbrowser.repo.search

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.MainActivityDelegate
import hu.arondev.uni.mobileprog.githubbrowser.R
import hu.arondev.uni.mobileprog.githubbrowser.user.page.RepositoryAdapter
import kotlinx.android.synthetic.main.repo_search_fragment.*
import kotlinx.android.synthetic.main.user_search_fragment.*
import java.lang.ClassCastException

class RepoSearchFragment : Fragment() {

    companion object {
        fun newInstance() = RepoSearchFragment()
    }

    private lateinit var viewModel: RepoSearchViewModel

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
        return inflater.inflate(R.layout.repo_search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(RepoSearchViewModel::class.java)

        viewModel.repos.observe(this, { repos ->
            val adapter = RepositoryAdapter(context!!, repos) { repo ->
                mainActivityDelegate.openRepositoryPage(repo.owner.login, repo.name)
            }
            repo_search_recyclerview.adapter = adapter
        })

        repo_search_button.setOnClickListener {
            mainActivityDelegate.hideKeyboard()
            viewModel.loadRepos(repo_search_input.text.toString())
        }
    }

}