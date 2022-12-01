package uz.invinsible.layouts.firebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.TokenWatcher
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FederatedAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import uz.invinsible.layouts.MainActivity
import uz.invinsible.layouts.api.ApiUsersActivity
import uz.invinsible.layouts.databinding.PhoneCodeLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage

class AuthCodeActivity : AppCompatActivity() {
    lateinit var binding: PhoneCodeLayoutBinding
    var auth = FirebaseAuth.getInstance()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PhoneCodeLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.codeOk.setOnClickListener {
           clickOK()
        }

        setFocus()
    }

    private fun clickOK() {
        if (
            binding.codeEdit1.text.isEmpty() ||
            binding.codeEdit2.text.isEmpty() ||
            binding.codeEdit3.text.isEmpty() ||
            binding.codeEdit4.text.isEmpty() ||
            binding.codeEdit5.text.isEmpty() ||
            binding.codeEdit6.text.isEmpty()
        ) {
            Toast.makeText(baseContext, "bo'sh joylarni to'ldiring", Toast.LENGTH_SHORT).show()
        } else {
            val token = intent.getStringExtra(DataStorage.putExtraKey)
            val number =
                "${binding.codeEdit1.text}${binding.codeEdit2.text}${binding.codeEdit3.text}${binding.codeEdit4.text}${binding.codeEdit5.text}${binding.codeEdit6.text}"

            val credential = PhoneAuthProvider.getCredential(token.toString(), number)
            signInWithPhoneWithCredential(credential)

        }
    }

    private fun signInWithPhoneWithCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val intent = Intent(applicationContext, ApiUsersActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error Code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFocus() {
        binding.codeEdit1.addTextChangedListener {
            if (binding.codeEdit1.length() == 1) {
                binding.codeEdit2.requestFocus()
            }
        }

//        binding.codeEdit1.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (binding.codeEdit1.length() == 1) {
//                    binding.codeEdit2.requestFocus()
//                }
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//
//        })
        binding.codeEdit2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (binding.codeEdit2.length() == 1) {
                    binding.codeEdit3.requestFocus()
                } else if (binding.codeEdit2.length() == 0) {
                    binding.codeEdit1.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.codeEdit3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEdit3.length() == 1) {
                    binding.codeEdit4.requestFocus()
                } else if (binding.codeEdit3.length() == 0) {
                    binding.codeEdit2.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.codeEdit4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEdit4.length() == 1) {
                    binding.codeEdit5.requestFocus()
                } else if (binding.codeEdit4.length() == 0) {
                    binding.codeEdit3.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.codeEdit5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEdit5.length() == 1) {
                    binding.codeEdit6.requestFocus()
                } else if (binding.codeEdit5.length() == 0) {
                    binding.codeEdit4.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.codeEdit6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEdit6.length() == 1) {
                    clickOK()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}