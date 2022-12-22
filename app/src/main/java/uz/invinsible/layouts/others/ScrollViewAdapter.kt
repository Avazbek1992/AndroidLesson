package uz.invinsible.layouts.others

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import uz.invinsible.layouts.R
import uz.invinsible.layouts.databinding.ItemForScrollBinding
import uz.invinsible.layouts.databinding.ScrollVeiwBinding

class ScrollViewAdapter : BaseAdapter() {
    lateinit var binding: ItemForScrollBinding
    override fun getCount(): Int {
        return 25
    }

    override fun getItem(p0: Int): Any {
        return 25
    }

    override fun getItemId(p0: Int): Long {
        return 25
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        binding =
            ItemForScrollBinding.inflate(LayoutInflater.from(p2?.context), p2, false)
        return binding.root
    }
}