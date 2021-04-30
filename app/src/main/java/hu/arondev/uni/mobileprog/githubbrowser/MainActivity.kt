package hu.arondev.uni.mobileprog.githubbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import hu.arondev.uni.mobileprog.core.data.IssueRepository
import hu.arondev.uni.mobileprog.core.data.RepoRepository
import hu.arondev.uni.mobileprog.core.data.UserRepository
import hu.arondev.uni.mobileprog.framework.rest.datasource.IssueHttpDataSource
import hu.arondev.uni.mobileprog.framework.rest.datasource.RepoHttpDataSource
import hu.arondev.uni.mobileprog.framework.rest.datasource.UserHttpDataSource
import hu.arondev.uni.mobileprog.githubbrowser.repo.search.RepoSearchFragment
import hu.arondev.uni.mobileprog.githubbrowser.user.page.UserPageFragment
import hu.arondev.uni.mobileprog.githubbrowser.user.search.UserSearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.app_toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

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
                // TODO: load current user (MainActivityDelegate)
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
}