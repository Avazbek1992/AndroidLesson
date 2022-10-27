package uz.invinsible.layouts.navigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import uz.invinsible.layouts.R
import uz.invinsible.layouts.recycle_view.MessagesFragment

class NavigationActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity_layout)

        supportActionBar?.elevation = 0.0f
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_id)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            it.isCheckable = true
            when (it.itemId) {
                R.id.ic_new_group_id -> replaceFragment(NewGroupFragment(), it.title.toString())
                R.id.nav_contacts_id -> replaceFragment(ContactsFragment(), it.title.toString())
                R.id.nav_calls_id -> replaceFragment(ContactsFragment(), it.title.toString())
                R.id.nav_people_nearby_id -> replaceFragment(
                    ContactsFragment(),
                    it.title.toString()
                )
                R.id.nav_saved_messages_id -> replaceFragment(
                    ContactsFragment(),
                    it.title.toString()
                )
                R.id.nav_setting_id -> replaceFragment(ContactsFragment(), it.title.toString())
                R.id.nav_invite_friends_id -> replaceFragment(
                    ContactsFragment(),
                    it.title.toString()
                )
                R.id.nav_telegram_features_id -> replaceFragment(
                    ContactsFragment(),
                    it.title.toString()
                )
            }

            true
        }
        replaceFragment(MessagesFragment(), resources.getString(R.string.chats))
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navigation_container_id, fragment)
            .commit()
        drawerLayout.close()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}