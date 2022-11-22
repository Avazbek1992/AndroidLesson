package uz.invinsible.layouts.firebase

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.invinsible.layouts.R
import uz.invinsible.layouts.database.MyDatabase
import uz.invinsible.layouts.databinding.ChatRegisterActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ChatRegisterActivityLayoutBinding
    var fireDatabase = FirebaseDatabase.getInstance()
        .getReferenceFromUrl("https://androidlesson-6c60c-default-rtdb.firebaseio.com/")
    private lateinit var myDatabase: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatRegisterActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myDatabase = MyDatabase(baseContext)
        val storage = DataStorage(baseContext)
        if (storage.readPhone() != "") {
            startActivity(Intent(baseContext, FirebaseMessengerActivity::class.java))
            finish()
        }

        supportActionBar?.hide()
        val dialog =  myDialog()
        binding.registerBtnId.setOnClickListener {
            dialog.show()
            if (binding.fishEdit.text.toString().isNotEmpty() && binding.emailEdit.text.toString()
                    .isNotEmpty() && binding.phoneEdit.text.toString().isNotEmpty()
            ) {
                fireDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        dialog.dismiss()
                        val fullName = binding.fishEdit.text.toString()
                        val phone = binding.phoneEdit.text.toString()
                        if (snapshot.child("Users").hasChild(phone)) {
                            println("fireDatabase if")
                            Toast.makeText(
                                baseContext,
                                "Siz allaqachon ro'yhaatdan o'tgansiz...",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            println("fireDatabase else")
                            fireDatabase
                                .child("Users")
                                .child(phone)
                                .child("name")
                                .setValue(fullName)

                            fireDatabase
                                .child("Users")
                                .child(phone)
                                .child("email")
                                .setValue(binding.emailEdit.text.toString())

                            myDatabase.insertUser(phone, fullName, phone)
                            val dataStorage = DataStorage(baseContext)
                            dataStorage.saveSharedPrefPhone(phone)
                            startActivity(
                                Intent(
                                    baseContext,
                                    FirebaseMessengerActivity::class.java
                                )
                            )
                            finish()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        myDialog().dismiss()
                    }
                })
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