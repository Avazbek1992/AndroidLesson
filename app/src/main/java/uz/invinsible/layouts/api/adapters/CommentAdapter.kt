package uz.invinsible.layouts.api.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import uz.invinsible.layouts.api.model.comments.CommentsItem
import uz.invinsible.layouts.databinding.ApiCommentItemBinding

class CommentAdapter(private val commentList: ArrayList<CommentsItem>) :
    BaseAdapter() {
    lateinit var binding: ApiCommentItemBinding
    override fun getCount(): Int {
        return commentList.size
    }

    override fun getItem(p0: Int): Any {
        return commentList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        binding = ApiCommentItemBinding.inflate(LayoutInflater.from(p2?.context), p2, false)
            binding.commentName.text = commentList[p0].name
            binding.commentEmail.text = commentList[p0].email
            binding.commentBody.text = commentList[p0].body
        return binding.root
    }
}