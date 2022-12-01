package uz.invinsible.layouts.firebase

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import uz.invinsible.layouts.api.ApiUsersActivity
import uz.invinsible.layouts.databinding.AuthPhoneLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage
import java.util.Objects
import java.util.concurrent.TimeUnit

class AuthPhoneActivity : AppCompatActivity() {
    lateinit var binding: AuthPhoneLayoutBinding
    private lateinit var callbacks: OnVerificationStateChangedCallbacks
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthPhoneLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()




        binding.sendBtn.setOnClickListener {
            callbacks = object : OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    val intent=Intent(this@AuthPhoneActivity,ApiUsersActivity::class.java)
startActivity(intent)
                    finish()
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(baseContext, e.message, Toast.LENGTH_SHORT).show()
                }

                override fun onCodeSent(token: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    val intent = Intent(baseContext, AuthCodeActivity::class.java)
                    intent.putExtra(DataStorage.putExtraKey, token)
                    startActivity(intent)
                }
            }

            val phoneAuthOptions = PhoneAuthOptions
                .newBuilder(auth)
                .setPhoneNumber("+998${binding.phoneEdit.text}")
                .setTimeout(60, TimeUnit.SECONDS)
                .setCallbacks(callbacks)
                .setActivity(this)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
        }

    }
}