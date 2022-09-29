package uz.invinsible.layouts.intent

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class SecondIntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val result = intent.getStringExtra("Key")
        findViewById<TextView>(R.id.intent_name_id).text = result

        findViewById<Button>(R.id.intent_share_id)
            .setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                }
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=uz.bm24.qrplayer_android"
                )
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    println(e.message)
                }
            }

        findViewById<Button>(R.id.intent_contact_us)
            .setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("mailto:" + "avazxon.ubaydullayev@gmail.com")
                }
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    println(e.message)
                }

            }
    }
}