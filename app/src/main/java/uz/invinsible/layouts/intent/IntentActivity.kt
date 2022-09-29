package uz.invinsible.layouts.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class IntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intent_layout)

        findViewById<Button>(R.id.intent_btn)
            .setOnClickListener {
                val intent = Intent(this, SecondIntentActivity::class.java)
                intent.putExtra("Key", "Avazbek Ubaydullayev")
                startActivity(intent)
            }


    }
}