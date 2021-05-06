package hu.arondev.uni.mobileprog.githubbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import hu.arondev.uni.mobileprog.githubbrowser.issue.AddIssueCommentFragment
import hu.arondev.uni.mobileprog.githubbrowser.issue.AddIssueFragment
import hu.arondev.uni.mobileprog.githubbrowser.issue.IssueBrowseFragment
import hu.arondev.uni.mobileprog.githubbrowser.issue.IssuePageFragment
import hu.arondev.uni.mobileprog.githubbrowser.repo.page.RepoPageFragment
import hu.arondev.uni.mobileprog.githubbrowser.repo.search.RepoSearchFragment
import hu.arondev.uni.mobileprog.githubbrowser.user.page.UserPageFragment
import hu.arondev.uni.mobileprog.githubbrowser.user.search.UserSearchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
        MainActivityDelegate {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, GitHubViewModelFactory).get(MainViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.app_toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val navView = findViewById<NavigationView>(R.id.nav_view)
        val navHeaderView = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null)
        val profileImageView = navHeaderView.findViewById<ShapeableImageView>(R.id.profile_picture)
        profileImageView.shapeAppearanceModel.toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, resources.getDimension(R.dimen.nav_profile_pic_radius))
                .build().also { profileImageView.shapeAppearanceModel = it }

        viewModel.currentUser.observe(this, { user ->
            navHeaderView.findViewById<TextView>(R.id.username).text = user.login
            navHeaderView.findViewById<TextView>(R.id.organization).text = user.company
            Picasso.get()
                    .load(user.avatar_url)
                    .placeholder(R.mipmap.ic_splash_round)
                    .error(R.mipmap.ic_splash_round)
                    .into(profileImageView)
            navView.addHeaderView(navHeaderView)
            viewModel.currentUser.removeObservers(this)
        })
        viewModel.getCurrentUser()

        nav_view.setNavigationItemSelectedListener(this)

        if(savedInstanceState == null) {
            nav_view.menu.findItem(R.id.nav_profile).isChecked = true
            nav_view.menu.performIdentifierAction(R.id.nav_profile, 0)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> supportFragmentManager.beginTransaction()
                    .replace(R.id.content, UserPageFragment.newInstance())
                    .commit()
            R.id.nav_user -> supportFragmentManager.beginTransaction()
                    .replace(R.id.content, UserSearchFragment.newInstance())
                    .commit()
            R.id.nav_repo -> supportFragmentManager.beginTransaction()
                    .replace(R.id.content, RepoSearchFragment.newInstance())
                    .commit()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun openRepositoryPage(user: String, repo: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, RepoPageFragment.newInstance(user, repo))
            .commit()
    }

    override fun openUserPage(user: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, UserPageFragment.newInstance(user))
                .commit()
    }

    override fun openIssueBrowsePage(user: String, repo: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, IssueBrowseFragment.newInstance(user, repo))
                .commit()
    }

    override fun openIssuePage(user: String, repo: String, issueNumber: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, IssuePageFragment.newInstance(user, repo, issueNumber))
            .commit()
    }

    override fun openAddIssuePage(user: String, repo: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, AddIssueFragment.newInstance(user, repo))
            .commit()
    }

    override fun openAddIssueCommentPage(user: String, repo: String, issueNumber: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, AddIssueCommentFragment.newInstance(user, repo, issueNumber))
            .commit()
    }

    override fun hideKeyboard() {
        val imm: InputMethodManager = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}