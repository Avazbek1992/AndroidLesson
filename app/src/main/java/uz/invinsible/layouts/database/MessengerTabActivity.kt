package uz.invinsible.layouts.database

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import uz.invinsible.layouts.R

class MessengerTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_activity_layout)

        supportActionBar?.hide()

        val tabLayout: TabLayout = findViewById(R.id.tab_layout_id)
        val viewPager: ViewPager = findViewById(R.id.view_pager_id)
        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragmentAndTitle(MessengerChat(), resources.getString(R.string.chat))
        adapter.addFragmentAndTitle(MessengerGroup(), resources.getString(R.string.group))
        adapter.addFragmentAndTitle(MessengerChannel(), resources.getString(R.string.channel))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_contacts)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_calls)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_person_add)
    }
}