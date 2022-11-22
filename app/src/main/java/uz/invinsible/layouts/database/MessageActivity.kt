package uz.invinsible.layouts.database

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R
import java.util.*
import kotlin.collections.ArrayList

class MessageActivity : AppCompatActivity() {
    companion object {
        var messageList = ArrayList<Message>()
        var from = 0
        var to = 0
        lateinit var recyclerView: RecyclerView
        lateinit var adapter: MessageAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_activity_layout)
        val database = MyDatabase(this)
        from = intent.getIntExtra("from", -1)
        to = intent.getIntExtra("to", -1)
        var messageList = database.selectMessages(from, to)
        val messageEdit: EditText = findViewById(R.id.message_edit_id)
        val sendBtn: ImageView = findViewById(R.id.send_btn_id)
        val micBtn: ImageView = findViewById(R.id.message_mic_btn_id)
        val attachBtn: ImageView = findViewById(R.id.message_attach_btn_id)
        //set recyclerView
        recyclerView = findViewById(R.id.message_recycler_view_id)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(
            messageList,
            from.toString(),
            "2",
            arrayOf("fromUser", "toUser")
        )
        recyclerView.adapter = adapter
        if (messageList.isNotEmpty())
            recyclerView.smoothScrollToPosition(messageList.size - 1)

        for (message in messageList) {
            println("id: ${message.messageId}")
            println("from: ${message.from}")
            println("to: ${message.to}")
            println("message: ${message.message}")
            println("date: ${message.currentDate}")
        }

        attachBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 101)
        }

        sendBtn.setOnClickListener {
            database.insertMessage(
                from,
                to,
                messageEdit.text.toString(),
                getCurrentDate()
            )
            messageEdit.setText("")
            messageList = database.selectMessages(from, to)
            adapter = MessageAdapter(
                messageList,
                from.toString(),
                "2",
                arrayOf("fromUser", "toUser")
            )
            recyclerView.adapter = adapter
            recyclerView.smoothScrollToPosition(messageList.size - 1)
        }

        messageEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(text: CharSequence, i: Int, i1: Int, i2: Int) {
                if (text.isEmpty()) {
                    sendBtn.visibility = View.GONE
                    micBtn.visibility = View.VISIBLE
                    attachBtn.visibility = View.VISIBLE
                } else {
                    sendBtn.visibility = View.VISIBLE
                    micBtn.visibility = View.GONE
                    attachBtn.visibility = View.GONE
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun getCurrentDate(): String {
        return "16:00"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            val uri = data?.data
            messageList.add(
                Message(
                    0,
                    from.toString(),
                    to.toString(),
                    "",
                    "15:00",
                    uri.toString()
                )
            )
            adapter = MessageAdapter(
                messageList,
                from.toString(),
                "2",
                arrayOf("fromUser", "toUser")
            )
            recyclerView.adapter = adapter
            recyclerView.smoothScrollToPosition(messageList.size - 1)
        }
    }
}