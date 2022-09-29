package uz.invinsible.layouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import uz.invinsible.layouts.intent.SecondIntentActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.views_intent)

        findViewById<Button>(R.id.intent_btn_id).setOnClickListener {
            startActivity(Intent(this, SecondIntentActivity::class.java))
        }
    }
}