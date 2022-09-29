package uz.invinsible.layouts.grid_view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import uz.invinsible.layouts.R

class GridViewCustomAdapter(val context: Context, val girdArrayList: ArrayList<GridModel>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return girdArrayList.size
    }

    override fun getItem(position: Int): Any {
        return girdArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(positon: Int, view: View?, groupView: ViewGroup?): View {
        val root =
            LayoutInflater.from(context).inflate(R.layout.grid_custom_layout, groupView, false)

        val image = root.findViewById<ImageView>(R.id.grid_image_id)
        val title = root.findViewById<TextView>(R.id.grid_view_title_id)

        image.setImageResource(girdArrayList[positon].image)
        title.text = girdArrayList[positon].title

        return root
    }
}