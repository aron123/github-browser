package hu.arondev.uni.mobileprog.githubbrowser.user.page

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import hu.arondev.uni.mobileprog.githubbrowser.GitHubViewModelFactory
import hu.arondev.uni.mobileprog.githubbrowser.R

class UserPageFragment : Fragment() {

    companion object {
        enum class ArgumentKeys {
            USERNAME
        }
        fun newInstance() = UserPageFragment()
    }

    private lateinit var viewModel: UserPageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(UserPageViewModel::class.java)
        val username = getArguments()?.getString(ArgumentKeys.USERNAME.toString())

        val profileImageView = view?.findViewById<ShapeableImageView>(R.id.profile_page_picture)
        profileImageView!!.shapeAppearanceModel.toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, resources.getDimension(R.dimen.nav_profile_pic_radius))
                .build().also { profileImageView.shapeAppearanceModel = it }

        viewModel.user.observe(this, { user ->
            Picasso.get()
                    .load(user.avatar_url)
                    .placeholder(R.drawable.ic_profile)
                    .error(R.drawable.ic_profile)
                    .into(profileImageView)

            view?.findViewById<TextView>(R.id.profile_page_username)?.text = user.login
            view?.findViewById<TextView>(R.id.profile_page_realname)?.text = user.name
            view?.findViewById<TextView>(R.id.profile_page_organization)?.text = user.company
            view?.findViewById<TextView>(R.id.profile_page_location)?.text = user.location
            view?.findViewById<TextView>(R.id.profile_page_bio)?.text = user.bio
            if (!user.hireable) {
                view?.findViewById<TextView>(R.id.profile_page_hireable)?.visibility = View.GONE
            }

            view?.findViewById<TextView>(R.id.profile_page_public_repos)?.text= resources.getQuantityString(
                    R.plurals.profile_page_public_repositories, user.public_repos, user.public_repos)
            view?.findViewById<TextView>(R.id.profile_page_followers)?.text = resources.getQuantityString(
                    R.plurals.profile_page_followers, user.followers, user.followers)
            view?.findViewById<TextView>(R.id.profile_page_following)?.text = resources.getQuantityString(
                    R.plurals.profile_page_following, user.following, user.following)
            // TODO: load repositories
        })

        try {
            viewModel.loadUser(username)
        } catch(ex: Exception) {
            Log.e("USER_PAGE_FRAGMENT", ex.stackTraceToString())
            Snackbar.make(view!!, R.string.user_load_error, Snackbar.LENGTH_LONG).show()
        }

    }

}