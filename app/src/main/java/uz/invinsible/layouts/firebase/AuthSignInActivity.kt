package uz.invinsible.layouts.firebase

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import uz.invinsible.layouts.R
import uz.invinsible.layouts.database.MessengerTabActivity
import uz.invinsible.layouts.databinding.LoginActivityLayoutBinding

class AuthSignInActivity : AppCompatActivity() {
    lateinit var binding: LoginActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val auth = FirebaseAuth.getInstance()
        binding.registerTvId.setOnClickListener {
            startActivity(Intent(this, AuthSignUpActivity::class.java))
            finish()
        }

        binding.loginBtnId.setOnClickListener {
            if (
                binding.loginEmailEditId.text?.isNotEmpty() == true
                && binding.loginPasswordEditId.text?.isNotEmpty() == true
            ) {
                myDialog().show()
                val email = binding.loginEmailEditId.text.toString()
                val password = binding.loginPasswordEditId.text.toString()
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() {
                    myDialog().dismiss()
                    if (it.isSuccessful) {
                        myDialog().dismiss()
                        super.startActivity(Intent(this, MessengerTabActivity::class.java))
                    } else {
                        myDialog().dismiss()
                        Toast.makeText(
                            this,
                            "Parol yoki login noto'g'ri",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


    private fun myDialog(): Dialog {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.sign_up_dialog_layout)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        return dialog
    }
}