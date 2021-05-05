package hu.arondev.uni.mobileprog.githubbrowser.user.page

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.shape.CornerFamily
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.MainActivityDelegate
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.user_page_fragment.*
import java.lang.ClassCastException

class UserPageFragment : Fragment() {

    companion object {
        enum class ArgumentKeys {
            USERNAME
        }

        fun newInstance() = UserPageFragment()

        fun newInstance(username: String): UserPageFragment {
            val args = Bundle()
            args.putString(ArgumentKeys.USERNAME.toString(), username)

            val fragment = UserPageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: UserPageViewModel

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
        return inflater.inflate(R.layout.user_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(UserPageViewModel::class.java)
        val username = getArguments()?.getString(ArgumentKeys.USERNAME.toString())

        val profileImageView = profile_page_picture
        profileImageView!!.shapeAppearanceModel.toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, resources.getDimension(R.dimen.nav_profile_pic_radius))
                .build().also { profileImageView.shapeAppearanceModel = it }

        viewModel.repositories.observe(this, { repos ->
            val repositoryAdapter = RepositoryAdapter(context!!, repos) { repo ->
                mainActivityDelegate.openRepositoryPage(repo.owner.login, repo.name)
            }
            user_page_repo_recycler_view.adapter = repositoryAdapter
        })

        viewModel.user.observe(this, { user ->
            Picasso.get()
                    .load(user.avatar_url)
                    .placeholder(R.drawable.ic_profile)
                    .error(R.drawable.ic_profile)
                    .into(profileImageView)

            if (username == null) {
                profile_page_follow_icon.visibility = View.INVISIBLE
            }

            profile_page_username.text = user.login
            profile_page_realname.text = user.name
            profile_page_organization.text = user.company
            profile_page_location.text = user.location
            profile_page_bio.text = user.bio
            profile_page_hireable.visibility= if (user.hireable) View.VISIBLE else View.GONE
            profile_page_public_repos.text= resources.getQuantityString(
                    R.plurals.profile_page_public_repositories, user.public_repos, user.public_repos)
            profile_page_followers.text = resources.getQuantityString(
                    R.plurals.profile_page_followers, user.followers, user.followers)
            profile_page_following.text = resources.getQuantityString(
                    R.plurals.profile_page_following, user.following, user.following)

            viewModel.loadRepositoriesOfUser(user.login)
        })

        try {
            viewModel.loadUser(username)
        } catch(ex: Exception) {
            Log.e("USER_PAGE_FRAGMENT", ex.stackTraceToString())
            Snackbar.make(view!!, R.string.user_load_error, Snackbar.LENGTH_LONG).show()
        }

    }

}