package uz.invinsible.layouts.database

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R

class MessengerChat : Fragment(), RecycleItemOnClick {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.chat_activity_layout, container, false)
        val myDatabase = MyDatabase(requireActivity())
        if (myDatabase.getUsersCount() == 0) {
            myDatabase.insertUser("Azizbek Mahmudjanov", "Salom")
            myDatabase.insertUser("Shahriyor Abubakriddinov", "nima gaplar")
        }

        val usersList = myDatabase.selectUsers()
        val recyclerView: RecyclerView = root.findViewById(R.id.chat_recycler_view_id)
        val recycleItemOnClick: RecycleItemOnClick = this
        val adapter = ChatRecyclerCustomAdapter(usersList, recycleItemOnClick)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter
        return root
    }

    override fun onItemClick(position: Int) {
        Log.d("ONCLICK: ", "Yes")
        val intent = Intent(requireActivity(), MessageActivity::class.java)
        if (position == 0) {
            intent.putExtra("from", 1)
            intent.putExtra("to", 2)
        } else if (position == 1){
            intent.putExtra("from", 2)
            intent.putExtra("to", 1)
        }
        startActivity(intent)
    }

}