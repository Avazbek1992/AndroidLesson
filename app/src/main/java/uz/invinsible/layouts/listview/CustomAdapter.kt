package uz.invinsible.layouts.listview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import uz.invinsible.layouts.R

class CustomAdapter(private val context: Context, private val arrayList: ArrayList<UserModel>) : BaseAdapter() {


    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val root : View = LayoutInflater.from(context).inflate(R.layout.custom_adapter_layout, viewGroup, false)
        val image = root.findViewById<ImageView>(R.id.custom_adapter_image_view_id)
        val title = root.findViewById<TextView>(R.id.custom_title)
        val subtitle = root.findViewById<TextView>(R.id.custom_subtitle)
        image.setImageResource(arrayList[position].getImage())
        title.text = arrayList[position].getName()
        subtitle.text = arrayList[position].getSubtitle()

        return root
    }
}