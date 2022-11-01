package uz.invinsible.layouts.database

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R
import java.util.*

class MessageActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_activity_layout)
        val database = MyDatabase(this)
        val from = intent.getIntExtra("from", -1)
        val to = intent.getIntExtra("to", -1)
        val messageList = database.selectMessages(from, to)
        val messageEdit: EditText = findViewById(R.id.message_edit_id)
        val sendBtn: ImageView = findViewById(R.id.send_btn_id)
        val micBtn: ImageView = findViewById(R.id.message_mic_btn_id)
        val attachBtn: ImageView = findViewById(R.id.message_attach_btn_id)
        //set recyclerView
        val recyclerView: RecyclerView = findViewById(R.id.message_recycler_view_id)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MessageAdapter(messageList, from)
        recyclerView.adapter = adapter

        sendBtn.setOnClickListener {
            database.insertMessage(from, to, messageEdit.text.toString(), getCurrentDate())
            messageEdit.setText("")
            adapter.notifyDataSetChanged()
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
}