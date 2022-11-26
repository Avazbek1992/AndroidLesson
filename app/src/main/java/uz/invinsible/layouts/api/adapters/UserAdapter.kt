package uz.invinsible.layouts.api.adapters

import android.annotation.SuppressLint
import android.icu.text.LocaleDisplayNames.UiListItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import uz.invinsible.layouts.R
import uz.invinsible.layouts.api.model.users.UsersItem

class UserAdapter(private val userList: ArrayList<UsersItem>) : BaseAdapter() {
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
        val root = LayoutInflater.from(parent?.context)
            .inflate(R.layout.api_users_item, parent, false)

        root.findViewById<TextView>(R.id.user_full_name).text = userList[postion].name
        root.findViewById<TextView>(R.id.username).text = userList[postion].username
        val un = userList[postion].name.split("\\s+".toRegex())
        val txt = if (un.size > 1) {
            un[0][0].plus(un[1][0].toString())
        } else {
            un[0][0].toString()
        }
        root.findViewById<TextView>(R.id.imageTXT).text = txt.uppercase()

        return root

    }
}