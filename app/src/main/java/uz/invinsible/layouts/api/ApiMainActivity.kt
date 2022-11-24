package uz.invinsible.layouts.api

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.invinsible.layouts.api.model.posts.PostItem
import uz.invinsible.layouts.databinding.ApiMianActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class ApiMainActivity : AppCompatActivity() {
    lateinit var binding: ApiMianActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ApiMianActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var arrayList = ArrayList<PostItem>()

        val retrofitBuild = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuild.getPosts()

        retrofitData.enqueue(object : Callback<List<PostItem>> {
            override fun onResponse(
                call: Call<List<PostItem>>,
                response: Response<List<PostItem>>
            ) {
                arrayList = response.body() as ArrayList<PostItem>
                val adapter = ApiListAdapter(arrayList)
                binding.ipaList.adapter = adapter
            }

            override fun onFailure(call: Call<List<PostItem>>, t: Throwable) {
                Toast.makeText(baseContext, "Muvaffaqiyatsiz: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        binding.ipaList.setOnItemClickListener { adapterView, view, position, l ->
            val intent = Intent(baseContext, CommentsActivity::class.java)
            intent.putExtra(DataStorage.putExtraKey, arrayList[position].id)
            startActivity(intent)
        }
    }
}