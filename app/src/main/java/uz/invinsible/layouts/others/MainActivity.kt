package uz.invinsible.layouts.others

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import uz.invinsible.layouts.R

class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_view_layout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


//        findViewById<Button>(R.id.intent_btn_id).setOnClickListener {
//            startActivity(Intent(this, SecondIntentActivity::class.java))
//        }
    }
}