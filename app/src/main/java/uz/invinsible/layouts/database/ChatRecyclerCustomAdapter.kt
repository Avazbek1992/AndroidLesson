package uz.invinsible.layouts.database

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R

class ChatRecyclerCustomAdapter(
    private val usersList: ArrayList<User>
) :
    RecyclerView.Adapter<ChatRecyclerCustomAdapter.ViewHolder>() {
    private val color = arrayOf(Color.BLUE, Color.RED)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_custom_layout, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgTxt.text = usersList[position].imgTxt
        holder.imgTxt.setBackgroundColor(color[position])
        holder.fullName.text = usersList[position].fullName
        holder.phoneNumber.text = usersList[position].lastMessage

        holder.itemView.setOnClickListener {
//            onItemClick.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgTxt: TextView = itemView.findViewById(R.id.contact_img_id)
        val fullName: TextView = itemView.findViewById(R.id.contact_name_id)
        val phoneNumber: TextView = itemView.findViewById(R.id.contact_phone_id)
    }
}