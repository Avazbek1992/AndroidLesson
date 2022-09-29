package uz.invinsible.layouts.shared_pref

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        val storage = DataStorage(this)
        findViewById<Button>(R.id.logout_id)
            .setOnClickListener {
                storage.saveSharedPrefProfile(false)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
    }
}