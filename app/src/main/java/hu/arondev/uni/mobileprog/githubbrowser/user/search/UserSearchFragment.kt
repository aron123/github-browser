package hu.arondev.uni.mobileprog.githubbrowser.user.search

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.shape.CornerFamily
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.MainActivityDelegate
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.user_item.*
import kotlinx.android.synthetic.main.user_search_fragment.*
import java.lang.ClassCastException

class UserSearchFragment : Fragment() {

    companion object {
        fun newInstance() = UserSearchFragment()
    }

    private lateinit var viewModel: UserSearchViewModel

    private lateinit var mainActivityDelegate: MainActivityDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            mainActivityDelegate = context as MainActivityDelegate
        } catch (e: ClassCastException) {
            throw ClassCastException()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(UserSearchViewModel::class.java)

        viewModel.users.observe(this) { users ->
            val adapter = UserSearchAdapter(context!!, users) { user ->
                mainActivityDelegate.openUserPage(user.login)
            }
            user_search_recyclerview.adapter = adapter
        }

        user_search_button.setOnClickListener {
            mainActivityDelegate.hideKeyboard()
            viewModel.loadUsers(user_search_input.text.toString())
        }
    }
}