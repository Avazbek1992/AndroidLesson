package uz.invinsible.layouts.optionmenu

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class OptionMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progress_bar)
        registerForContextMenu(findViewById(R.id.id_my_name))


        val textView: TextView = findViewById(R.id.id_my_name)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 200, 0, 0)
        textView.layoutParams = layoutParams

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var str = ""
        str = when (item.itemId) {
            R.id.search_id -> "Search clicked"
            R.id.setting_id -> "Setting clicked"
            R.id.about_id -> "About clicked"
            R.id.contact_us_id -> "Contact Us clicked"
            R.id.share_id -> "Share clicked"
            else -> "More Setting"
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        return super.onContextItemSelected(item)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var str = ""
        str = when (item.itemId) {
            R.id.search_id -> "Search clicked"
            R.id.setting_id -> "Setting clicked"
            R.id.about_id -> "About clicked"
            R.id.contact_us_id -> "Contact Us clicked"
            R.id.share_id -> "Share clicked"
            else -> "More Setting"
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        return super.onOptionsItemSelected(item)
    }
}