package uz.invinsible.layouts.api

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ParcelableSparseArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.invinsible.layouts.api.adapters.UserAdapter
import uz.invinsible.layouts.api.model.users.UsersItem
import uz.invinsible.layouts.databinding.ApiMianActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage


class ApiUsersActivity : AppCompatActivity() {
    lateinit var binding: ApiMianActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ApiMianActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val storage = DataStorage(this)
        var userList = ArrayList<UsersItem>()
        val dialog = storage.myDialog()
        dialog.show()
        val retrofitClient = Client()
        retrofitClient.getRetrofit().getUsers()
            .enqueue(object : Callback<List<UsersItem>> {
                override fun onResponse(
                    call: Call<List<UsersItem>>,
                    response: Response<List<UsersItem>>
                ) {
                    if (response.isSuccessful) {
                        dialog.dismiss()
                        userList = response.body() as ArrayList<UsersItem>
                        val adapter = UserAdapter(userList)
                        binding.ipaList.adapter = adapter
                    } else {
                        dialog.dismiss()
                    }
                }

                override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(baseContext, "Muvaffaqiyatsiz: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        binding.ipaList.setOnItemClickListener { adapterView, view, position, l ->
            val array = ArrayList<String>()
            array.add(userList[position].name)
            array.add(userList[position].address.city.plus(" ").plus(userList[position].address.zipcode))
            array.add(userList[position].phone)
            array.add(userList[position].email)
            array.add(userList[position].website)
            array.add(userList[position].address.geo.lat.plus(",").plus(userList[position].address.geo.lng))

            val intent = Intent(baseContext, ProfileActivity::class.java)
            intent.putExtra(DataStorage.putExtraKey, array)
            startActivity(intent)
        }
    }
}