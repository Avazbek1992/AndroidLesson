package uz.invinsible.layouts.firebase

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.invinsible.layouts.database.Message
import uz.invinsible.layouts.database.MessageAdapter
import uz.invinsible.layouts.databinding.MessageActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage
import java.time.LocalTime

class ChatActivity : AppCompatActivity() {
    lateinit var binding: MessageActivityLayoutBinding
    private lateinit var userMobile: String
    lateinit var getMobile: String
    var chatId = 1
    lateinit var messageList: ArrayList<Message>
    lateinit var adapter: MessageAdapter
    private val firebaseDatabase = FirebaseDatabase.getInstance()
        .getReferenceFromUrl("https://androidlesson-6c60c-default-rtdb.firebaseio.com/")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MessageActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val storage = DataStorage(baseContext)
        userMobile = storage.readPhone()!!
        getMobile = intent.getStringExtra(DataStorage.putExtraKey)!!
        val actionBarTitle = intent.getStringExtra(DataStorage.putExtraNameKey)!!
        println("User Mobile: $userMobile")
        println("Get Mobile: $getMobile")
        binding.fullNameId.text = actionBarTitle
        binding.seenId.text = "offline"
        binding.actionBarImgId.text = storage.getIMGTxt(actionBarTitle)
        binding.messageEditId.addTextChangedListener {
            if (binding.messageEditId.text.isNotEmpty()) {
                binding.sendBtnId.visibility = View.VISIBLE
                binding.messageAttachBtnId.visibility = View.GONE
                binding.messageMicBtnId.visibility = View.GONE
            } else {
                binding.sendBtnId.visibility = View.GONE
                binding.messageAttachBtnId.visibility = View.VISIBLE
                binding.messageMicBtnId.visibility = View.VISIBLE
            }
        }

        var fromUser = ""
        var toUser = ""
        messageList = ArrayList()
        firebaseDatabase.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
//          get User from and to
                fromUser = snapshot.child("Users").child(userMobile).child("name").value.toString()
                toUser = snapshot.child("Users").child(getMobile).child("name").value.toString()
                println("Messages Count: ${snapshot.child("chats").childrenCount}")
                messageList.clear()
                if (snapshot.hasChild("chats")) {
                    var k = 0
                    println("MessagesActivity: $snapshot")
                    for (messages in snapshot.child("chats").children) {
                        println("Chats Result: ${messages.toString()}")
                        println("Chats Result: ${messages.value}")
                        chatId = (snapshot.child("chats").childrenCount + 1).toInt()
                        val from = messages.child("from").value.toString()
                        val to = messages.child("to").value.toString()
                        val message = messages.child("message").value.toString()
                        val time = messages.child("time").value.toString()
                        messageList.add(
                            Message(
                                k++,
                                from,
                                to,
                                message,
                                time,
                                ""
                            )
                        )
                    }
                }
//                messageList.sortByDescending { chatId }
                adapter = MessageAdapter(
                    baseContext,
                    messageList,
                    userMobile,
                    getMobile,
                    arrayOf(fromUser, toUser)
                )
                binding.messageRecyclerViewId.adapter = adapter
                binding.messageRecyclerViewId.layoutManager = LinearLayoutManager(baseContext)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        adapter = MessageAdapter(
            baseContext,
            messageList,
            userMobile,
            getMobile,
            arrayOf(fromUser, toUser)
        )

        binding.messageRecyclerViewId.adapter = adapter
        binding.messageRecyclerViewId.layoutManager = LinearLayoutManager(this)


        binding.sendBtnId.setOnClickListener {
            firebaseDatabase.child("chats").child(chatId.toString()).child("from")
                .setValue(userMobile)
            firebaseDatabase.child("chats").child(chatId.toString()).child("to").setValue(getMobile)
            firebaseDatabase.child("chats").child(chatId.toString()).child("message")
                .setValue(binding.messageEditId.text.toString())
            firebaseDatabase.child("chats").child(chatId.toString()).child("time")
                .setValue(LocalTime.now())

            binding.messageEditId.setText("")
        }

        binding.actionBarBack.setOnClickListener {
            super.onBackPressed()
        }
    }

}