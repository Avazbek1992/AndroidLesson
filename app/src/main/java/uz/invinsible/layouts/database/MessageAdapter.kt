package uz.invinsible.layouts.database

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R
import uz.invinsible.layouts.shared_pref.DataStorage
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MessageAdapter(
    private val context: Context,
    private val messageList: ArrayList<Message>,
    private val userId: String,
    private val getMobile: String,
    private val names: Array<String>
) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    private lateinit var database: MyDatabase
    private lateinit var storage: DataStorage
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        database = MyDatabase(context)
        storage = DataStorage(parent.context)
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.message_items, parent, false)

        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("MessagesAdapter: ${messageList[position].message}")
        if (userId == messageList[position].from && getMobile == messageList[position].to ||
            userId == messageList[position].to && getMobile == messageList[position].from
        ) {
            if (messageList[position].from == userId && messageList[position].imgUrl.isEmpty()) {//right
                holder.rightIMGText.text = storage.getIMGTxt(names[0])
                holder.rightUserName.text = names[0]
                holder.rightMessage.text = messageList[position].message
                holder.rightMessageDate.text = currentDate(messageList[position].currentDate)
                holder.rightLayout.visibility = View.VISIBLE
            } else if (messageList[position].imgUrl.isEmpty()) {//left
                holder.leftIMGText.text = storage.getIMGTxt(names[1])
                holder.leftUserName.text = names[1]
                holder.leftMessage.text = messageList[position].message
                holder.leftMessageDate.text = messageList[position].currentDate
                holder.leftLayout.visibility = View.VISIBLE
            } else if (messageList[position].from == userId && messageList[position].imgUrl.isNotEmpty()) {
                holder.imgRight.setImageURI(Uri.parse((messageList[position].imgUrl)))
                holder.imageRightLayout.visibility = View.VISIBLE
            } else {
                holder.imgLeft.setImageURI(Uri.parse((messageList[position].imgUrl)))
                holder.imageLeftLayout.visibility = View.VISIBLE
            }
        }
//        if (messageList[position].from == userId && messageList[position].imgUrl.isEmpty()) {//right
//            holder.rightIMGText.text = database.getUser(messageList[position].from).imgTxt
//            holder.rightUserName.text = database.getUser(messageList[position].from).fullName
//            holder.rightMessage.text = messageList[position].message
//            holder.rightMessageDate.text = messageList[position].currentDate
//            holder.rightLayout.visibility = View.VISIBLE
//        } else if (messageList[position].imgUrl.isEmpty()) {//left
//            holder.leftIMGText.text = database.getUser(messageList[position].from).imgTxt
//            holder.leftUserName.text = database.getUser(messageList[position].from).fullName
//            holder.leftMessage.text = messageList[position].message
//            holder.leftMessageDate.text = messageList[position].currentDate
//            holder.leftLayout.visibility = View.VISIBLE
//        } else if (messageList[position].from == userId && messageList[position].imgUrl.isNotEmpty()) {
//            holder.imgRight.setImageURI(Uri.parse((messageList[position].imgUrl)))
//            holder.imageRightLayout.visibility = View.VISIBLE
//        } else {
//            holder.imgLeft.setImageURI(Uri.parse((messageList[position].imgUrl)))
//            holder.imageLeftLayout.visibility = View.VISIBLE
//        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun currentDate(currentDate: String): String {

        val simpleDateFormat = SimpleDateFormat("hh:mm")
        return simpleDateFormat.format(Date())
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val leftLayout: LinearLayout = itemView.findViewById(R.id.message_left_layout)
        val rightLayout: LinearLayout = itemView.findViewById(R.id.message_right_layout)
        val imageLeftLayout: LinearLayout = itemView.findViewById(R.id.chat_image_left_layout)
        val imageRightLayout: LinearLayout = itemView.findViewById(R.id.chat_image_right_layout)

        val leftIMGText: TextView = itemView.findViewById(R.id.contact_left_img_id)
        val leftUserName: TextView = itemView.findViewById(R.id.left_user_name_id)
        val leftMessage: TextView = itemView.findViewById(R.id.left_message_id)
        val leftMessageDate: TextView = itemView.findViewById(R.id.left_message_date_id)

        val rightIMGText: TextView = itemView.findViewById(R.id.contact_right_img_id)
        val rightUserName: TextView = itemView.findViewById(R.id.right_user_name_id)
        val rightMessage: TextView = itemView.findViewById(R.id.right_message_id)
        val rightMessageDate: TextView = itemView.findViewById(R.id.right_message_date_id)

        val imgLeft: ImageView = itemView.findViewById(R.id.chat_left_image_id)
        val imgRight: ImageView = itemView.findViewById(R.id.chat_right_image_id)
    }

}
