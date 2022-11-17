package uz.invinsible.layouts.firebase

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import uz.invinsible.layouts.R
import uz.invinsible.layouts.databinding.RegisterActivityLayoutBinding

class AuthSignUpActivity : AppCompatActivity() {
    private lateinit var binding: RegisterActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val auth = FirebaseAuth.getInstance()
        binding.registerBtnId.setOnClickListener {
            if (binding.registerEmailEditId.text?.isNotEmpty() == true
                && binding.registerPasswordEditId.text?.isNotEmpty() == true
            ) {
                if (binding.registerPasswordEditId.text?.toString()?.length!! > 6) {
                    if (binding.registerPasswordEditId.text.toString() == binding.registerConfirmPasswordEditId.text.toString()) {
                        myDialog().show()
                        val email: String = binding.registerEmailEditId.text.toString()
                        val password = binding.registerPasswordEditId.text.toString()
                        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                            if (it.isSuccessful) {
                                myDialog().dismiss()
                                Toast.makeText(
                                    this,
                                    "Siz ro'yhatdan muvaffaqiyatli o'tdingiz",
                                    Toast.LENGTH_SHORT
                                ).show()
                                super.startActivity(Intent(this, AuthSignInActivity::class.java))
                            } else {
                                myDialog().dismiss()
                                Toast.makeText(
                                    this,
                                    "email farmati noto'g'ri",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Parolni tasdiqlash noto'g'ri kiritildi",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Parol uzunligi 6tadan kam",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


        binding.loginTvId.setOnClickListener {
            startActivity(Intent(this, AuthSignInActivity::class.java))
            finish()
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