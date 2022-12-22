package uz.invinsible.layouts.others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.invinsible.layouts.R

class PicassoTestAdapter(val arrayList: ArrayList<ItemModelTest>):RecyclerView.Adapter<PicassoTestAdapter.ViewHolder>(){
     class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
         val name = itemView.findViewById<TextView>(R.id.picassoText)
         val image:ImageView = itemView.findViewById(R.id.PicassoIMG)

     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
  val root=LayoutInflater.from(parent.context).inflate(R.layout.test_picass_item,parent,false)
         return ViewHolder(root)
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         Picasso.get().load(arrayList[position].url).into(holder.image)
         holder.name.text=arrayList[position].name
     }

     override fun getItemCount(): Int {
        return arrayList.size
     }

 }