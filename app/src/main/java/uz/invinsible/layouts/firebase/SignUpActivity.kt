package uz.invinsible.layouts.firebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.invinsible.layouts.databinding.ChatRegisterActivityLayoutBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ChatRegisterActivityLayoutBinding
    var fireDatabase = FirebaseDatabase.getInstance()
        .getReferenceFromUrl("https://androidlesson-6c60c-default-rtdb.firebaseio.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatRegisterActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.registerBtnId.setOnClickListener {
            if (binding.fishEdit.text.toString().isNotEmpty() && binding.emailEdit.text.toString()
                    .isNotEmpty() && binding.phoneEdit.text.toString().isNotEmpty()
            ) {
                println("fireDatabase working")
                fireDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.child("Users").hasChild(binding.phoneEdit.text.toString())) {
                            println("fireDatabase if")
                            Toast.makeText(
                                baseContext,
                                "Siz allaqachon ro'yhaatdan o'tgansiz...",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            println("fireDatabase else")
                            snapshot.child("Users").child(binding.phoneEdit.text.toString())
                                .child(binding.fishEdit.text.toString())
                            snapshot.child("Users").child(binding.phoneEdit.text.toString())
                                .child(binding.emailEdit.text.toString())
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })
            }
        }
    }
}