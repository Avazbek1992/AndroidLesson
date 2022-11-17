package uz.invinsible.layouts.shared_pref

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class RegisterActivity : AppCompatActivity() {

    var gmail: EditText? = null
    var password: EditText? = null
    var confPassword: EditText? = null
    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        val storage = DataStorage(this)

        if (storage.readSharePrefProfile("check")) {
            startActivity(Intent(this, Activity::class.java))
            finish()
        }

        gmail = findViewById(R.id.gmail_edit_id)
        password = findViewById(R.id.password_edit_id)
        confPassword = findViewById(R.id.confirm_password_edit_id)
        button = findViewById(R.id.register)

        button?.setOnClickListener {
            println("Button clicked")
            if (password?.text.toString() == confPassword?.text.toString()) {
                startActivity(Intent(this, LoginActivity::class.java))
                storage.saveSharedPrefPhone(gmail?.text.toString(), password?.text.toString())
            } else {
                Toast.makeText(this, "Confirm xato!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        findViewById<TextView>(R.id.to_login)
            .setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
    }
}
