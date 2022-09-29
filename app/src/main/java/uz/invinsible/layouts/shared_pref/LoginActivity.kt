package uz.invinsible.layouts.shared_pref

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class LoginActivity : AppCompatActivity() {

    var gmail: EditText? = null
    var password: EditText? = null
    var button: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        gmail = findViewById(R.id.login_gmail_edit_id)
        password = findViewById(R.id.login_password_edit_id)
        button = findViewById(R.id.login)

        findViewById<TextView>(R.id.to_register_id)
            .setOnClickListener {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
        val storage = DataStorage(this)
        val gmailText = storage.readSharePref("mail")
        val passwordText = storage.readSharePref("pass")

        button?.setOnClickListener {
            if (gmailText == gmail?.text.toString()
                && passwordText == password?.text.toString()){
                startActivity(Intent(this, Activity::class.java))
                storage.saveSharedPrefProfile(true)
            }else {
                Toast.makeText(this, "Paral yoki login noto'g'ri kiritdingiz!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}