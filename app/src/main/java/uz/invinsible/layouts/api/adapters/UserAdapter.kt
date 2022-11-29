package uz.invinsible.layouts.api.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import uz.invinsible.layouts.api.model.photos.PhotosItem
import uz.invinsible.layouts.api.model.users.UsersItem
import uz.invinsible.layouts.databinding.ApiUsersItemBinding

class UserAdapter(
    private val userList: ArrayList<UsersItem>,
    private val photoList: ArrayList<PhotosItem>
) : BaseAdapter() {
    lateinit var binding: ApiUsersItemBinding
    override fun getCount(): Int {
        return userList.size
    }

    override fun getItem(position: Int): Any {
        return userList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder", "MissingInflatedId")
    override fun getView(postion: Int, view: View?, parent: ViewGroup?): View {
        binding = ApiUsersItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        binding.userFullName.text = userList[postion].name
        binding.username.text = userList[postion].username
        if (photoList[postion].url.isEmpty()) {
            val un = userList[postion].name.split("\\s+".toRegex())
            val txt = if (un.size > 1) {
                un[0][0].plus(un[1][0].toString())
            } else {
                un[0][0].toString()
            }
            binding.imageTXT.text = txt.uppercase()
            binding.userImage.visibility = View.GONE
        } else {
            binding.imageTXT.visibility = View.GONE
            Picasso.get().load(photoList[postion].url).into(binding.userImage)
        }
        return binding.root
    }
}