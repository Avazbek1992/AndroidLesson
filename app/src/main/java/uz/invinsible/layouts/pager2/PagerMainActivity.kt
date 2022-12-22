package uz.invinsible.layouts.pager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import uz.invinsible.layouts.R

class PagerMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pager_main_activity_layout)

        val viewPager2: ViewPager2 = findViewById(R.id.view_pager2_id)
        val arrayList: ArrayList<PagerItem> = ArrayList()
        val imageID = arrayOf(
            R.drawable.ic_beckham2,
            R.drawable.ic_gerrard,
            R.drawable.ic_beckham2,
            R.drawable.ic_gerrard,
            R.drawable.ic_beckham2,
            R.drawable.ic_gerrard,
            R.drawable.ic_beckham2,
            R.drawable.ic_gerrard,
            R.drawable.ic_beckham2,
            R.drawable.ic_gerrard
        )

        val title = arrayOf(
            resources.getString(R.string.beckham_title),
            resources.getString(R.string.gerrard_title),
            resources.getString(R.string.beckham_title),
            resources.getString(R.string.gerrard_title),
            resources.getString(R.string.beckham_title),
            resources.getString(R.string.gerrard_title),
            resources.getString(R.string.beckham_title),
            resources.getString(R.string.gerrard_title),
            resources.getString(R.string.beckham_title),
            resources.getString(R.string.gerrard_title)
        )

        val subtitle = arrayOf(
            resources.getString(R.string.beckham_subtitle),
            resources.getString(R.string.gerrard_subtitle),
            resources.getString(R.string.beckham_subtitle),
            resources.getString(R.string.gerrard_subtitle),
            resources.getString(R.string.beckham_subtitle),
            resources.getString(R.string.gerrard_subtitle),
            resources.getString(R.string.beckham_subtitle),
            resources.getString(R.string.gerrard_subtitle),
            resources.getString(R.string.beckham_subtitle),
            resources.getString(R.string.gerrard_subtitle)
        )

        for (i in title.indices) {
            val pagerItem = PagerItem()
            pagerItem.setImageID(imageID[i])
            pagerItem.setTitle(title[i])
            pagerItem.setSubtitle(subtitle[i])
            arrayList.add(pagerItem)
        }

        viewPager2.adapter = PagerAdapter(arrayList)
    }
}