package uz.invinsible.layouts.pager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R

class PagerAdapter(private val arrayList: ArrayList<PagerItem>) :
    RecyclerView.Adapter<PagerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.pager_item, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pagerItem = arrayList[position]
        holder.image.setImageResource(pagerItem.imageID)
        holder.title.text = pagerItem.title
        holder.subtitle.text = pagerItem.subtitle
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.pager_main_image_view_id)
        val title: TextView = itemView.findViewById(R.id.pager_main_title_id)
        val subtitle: TextView = itemView.findViewById(R.id.pager_main_subtitle_id)
    }
}