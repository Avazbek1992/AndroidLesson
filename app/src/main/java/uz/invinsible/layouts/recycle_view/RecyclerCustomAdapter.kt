package uz.invinsible.layouts.recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R

class RecyclerCustomAdapter(
    private val arrayList: ArrayList<Contacts>
) :
    RecyclerView.Adapter<RecyclerCustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_custom_layout, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgTxt.text = arrayList[position].imgTxt
        println(arrayList[position].color)
        holder.imgTxt.setBackgroundColor(arrayList[position].color)
        holder.fullName.text = arrayList[position].fullName
        holder.phoneNumber.text = arrayList[position].phoneNumber

        holder.itemView.setOnClickListener {
//            onItemClick.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgTxt: TextView = itemView.findViewById(R.id.contact_img_id)
        val fullName: TextView = itemView.findViewById(R.id.contact_name_id)
        val phoneNumber: TextView = itemView.findViewById(R.id.contact_phone_id)
    }
}