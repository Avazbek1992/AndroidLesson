package uz.invinsible.layouts.api.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import uz.invinsible.layouts.R
import uz.invinsible.layouts.api.model.posts.PostItem

class PostAdapter(val arrayList: ArrayList<PostItem>) : BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder", "MissingInflatedId")
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        val root = LayoutInflater.from(parent?.context).inflate(R.layout.api_item_latout, parent, false)

        root.findViewById<TextView>(R.id.api_title).text = arrayList[position].title
        root.findViewById<TextView>(R.id.api_body).text = arrayList[position].body

        return root
    }
}