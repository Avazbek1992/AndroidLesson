package uz.invinsible.layouts.firebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.invinsible.layouts.database.FirebaseMessengerCustomAdapter
import uz.invinsible.layouts.database.MyDatabase
import uz.invinsible.layouts.database.RecycleItemOnClick
import uz.invinsible.layouts.database.User
import uz.invinsible.layouts.databinding.FirebaseMessengerActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage

class FirebaseMessengerActivity : AppCompatActivity(), RecycleItemOnClick {

    private lateinit var binding: FirebaseMessengerActivityLayoutBinding
    private val firebaseDatabase = FirebaseDatabase.getInstance()
        .getReferenceFromUrl("https://androidlesson-6c60c-default-rtdb.firebaseio.com/")
    var phone = ""
    lateinit var userList: ArrayList<User>
    lateinit var myDatabase: MyDatabase
    lateinit var onClick: RecycleItemOnClick
    lateinit var adapter: FirebaseMessengerCustomAdapter
    lateinit var storage: DataStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirebaseMessengerActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        storage = DataStorage(baseContext)
        phone = storage.readPhone().toString()
        println("MY_PHONE: $phone")
        myDatabase = MyDatabase(baseContext)
        userList = ArrayList()
        onClick = this
        val dialog = storage.myDialog(this)
        dialog.show()
        firebaseDatabase.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                val userCount = snapshot.child("Users").childrenCount
                if (userCount > 0) {
                    for (user in snapshot.child("Users").children) {
                        val getPhone = user.key.toString()
                        if (getPhone != phone) {
                            val fullName = user.child("name").value.toString()
//                            userList.add(
//                                User(
//                                    userList.size + 1,
//                                    getPhone,
//                                    fullName,
//                                    getPhone,
//                                    storage.getIMGTxt(fullName)
//                                )
//                            )
                            myDatabase.insertUser(getPhone, fullName, getPhone)
                        }
                    }
                    setRecyclerView()
                }
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }
        })
        setRecyclerView()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(baseContext, ChatActivity::class.java)
        intent.putExtra(storage.putExtraKey, userList[position].phone)
        intent.putExtra(storage.putExtraNameKey, userList[position].fullName)
        startActivity(intent)
    }

    private fun setRecyclerView() {
        userList.clear()
        userList = myDatabase.selectUsers()
        adapter = FirebaseMessengerCustomAdapter(userList, onClick)
        binding.chatRecyclerViewId.adapter = adapter
        binding.chatRecyclerViewId.layoutManager = LinearLayoutManager(this)
    }

}