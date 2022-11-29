package uz.invinsible.layouts.api.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import uz.invinsible.layouts.R
import uz.invinsible.layouts.api.model.posts.PostItem
import uz.invinsible.layouts.databinding.ApiPostsItemLatoutBinding

class PostAdapter(val arrayList: ArrayList<PostItem>, val postId: Int) : BaseAdapter() {
    lateinit var binding: ApiPostsItemLatoutBinding
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

        binding =
            ApiPostsItemLatoutBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        binding.apiTitle.text = arrayList[position].title
        binding.apiBody.text = arrayList[position].body

        return binding.root
    }
}