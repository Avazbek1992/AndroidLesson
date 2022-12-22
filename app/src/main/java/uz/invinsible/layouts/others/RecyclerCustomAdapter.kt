package uz.invinsible.layouts.others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.databinding.ItemForScrollBinding

class RecyclerCustomAdapter : RecyclerView.Adapter<RecyclerCustomAdapter.ViewHolder>() {
    lateinit var binding: ItemForScrollBinding

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemForScrollBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 25
    }
}