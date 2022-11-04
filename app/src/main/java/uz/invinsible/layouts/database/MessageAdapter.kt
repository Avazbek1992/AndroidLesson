package uz.invinsible.layouts.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R

class MessageAdapter(private val messageList: ArrayList<Message>, private val userId: Int) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    private lateinit var database: MyDatabase
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        database = MyDatabase(parent.context)
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.message_items, parent, false)

        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (messageList[position].from == userId) {//right
            holder.rightIMGText.text = database.getUser(messageList[position].from).imgTxt
            holder.rightUserName.text = database.getUser(messageList[position].from).fullName
            holder.rightMessage.text = messageList[position].message
            holder.rightMessageDate.text = messageList[position].currentDate
            holder.leftLayout.visibility = View.GONE
        } else {//left
            holder.leftIMGText.text = database.getUser(messageList[position].from).imgTxt
            holder.leftUserName.text = database.getUser(messageList[position].from).fullName
            holder.leftMessage.text = messageList[position].message
            holder.leftMessageDate.text = messageList[position].currentDate
            holder.rightLayout.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val leftLayout: LinearLayout = itemView.findViewById(R.id.message_left_layout)
        val rightLayout: LinearLayout = itemView.findViewById(R.id.message_right_layout)

        val leftIMGText: TextView = itemView.findViewById(R.id.contact_left_img_id)
        val leftUserName: TextView = itemView.findViewById(R.id.left_user_name_id)
        val leftMessage: TextView = itemView.findViewById(R.id.left_message_id)
        val leftMessageDate: TextView = itemView.findViewById(R.id.left_message_date_id)

        val rightIMGText: TextView = itemView.findViewById(R.id.contact_right_img_id)
        val rightUserName: TextView = itemView.findViewById(R.id.right_user_name_id)
        val rightMessage: TextView = itemView.findViewById(R.id.right_message_id)
        val rightMessageDate: TextView = itemView.findViewById(R.id.right_message_date_id)
    }

}
