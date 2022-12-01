package uz.invinsible.layouts.api

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.invinsible.layouts.api.adapters.UserAdapter
import uz.invinsible.layouts.api.model.photos.PhotosItem
import uz.invinsible.layouts.api.model.users.UsersItem
import uz.invinsible.layouts.databinding.ApiMainActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage


class ApiUsersActivity : AppCompatActivity() {
    lateinit var binding: ApiMainActivityLayoutBinding
    lateinit var dialog: Dialog
    var userList = ArrayList<UsersItem>()
    private val retrofitClient = Client()
   private var photoList = ArrayList<PhotosItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ApiMainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val storage = DataStorage(this)
        dialog = storage.myDialog()
        dialog.show()
        retrofitClient.getRetrofit().getUsers()
            .enqueue(object : Callback<List<UsersItem>> {
                override fun onResponse(
                    call: Call<List<UsersItem>>,
                    response: Response<List<UsersItem>>
                ) {
                    if (response.isSuccessful) {
                        dialog.dismiss()
                        userList = response.body() as ArrayList<UsersItem>
                        getPhotos()
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


        binding.ipaMainList.setOnItemClickListener { adapterView, view, position, l ->
            val array = ArrayList<String>()
            array.add(
                userList[position].address.city.plus(" ").plus(userList[position].address.zipcode)
            )
            array.add(photoList[position].url)
            array.add(userList[position].phone)
            array.add(userList[position].email)
            array.add(userList[position].website)
            array.add(
                userList[position].address.geo.lat.plus(",")
                    .plus(userList[position].address.geo.lng)
            )
            array.add(userList[position].id.toString())

            val intent = Intent(baseContext, ApiProfileActivity::class.java)
            intent.putExtra(DataStorage.putExtraKey, array)
            startActivity(intent)
        }
    }

    fun getPhotos() {
        retrofitClient.getRetrofit().getPhotos()
            .enqueue(object : Callback<List<PhotosItem>> {
                override fun onResponse(
                    call: Call<List<PhotosItem>>,
                    response: Response<List<PhotosItem>>
                ) {
                    if (response.isSuccessful) {
                        dialog.dismiss()
                        photoList = response.body() as ArrayList<PhotosItem>
                        val adapter = UserAdapter(userList, photoList)
                        binding.ipaMainList.adapter = adapter
                    } else {
                        dialog.dismiss()
                    }
                }

                override fun onFailure(call: Call<List<PhotosItem>>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(baseContext, "Muvaffaqiyatsiz: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}