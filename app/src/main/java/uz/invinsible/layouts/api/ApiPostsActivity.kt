package uz.invinsible.layouts.api

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.invinsible.layouts.api.adapters.PostAdapter
import uz.invinsible.layouts.api.model.posts.PostItem
import uz.invinsible.layouts.databinding.ApiPostsItemLatoutBinding
import uz.invinsible.layouts.databinding.ApiPostsLlayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage

class ApiPostsActivity : AppCompatActivity() {
    lateinit var binding: ApiPostsLlayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ApiPostsLlayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var postList = ArrayList<PostItem>()
        val client = Client()
        client.getRetrofit().getPosts().enqueue(object : Callback<List<PostItem>> {
            override fun onResponse(
                call: Call<List<PostItem>>,
                response: Response<List<PostItem>>
            ) {
                if (response.isSuccessful) {
                    postList = response.body() as ArrayList<PostItem>
                    val adapter = PostAdapter(postList, intent.getIntExtra(DataStorage.putExtraKey, 0))
                    binding.ipaList.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<PostItem>>, t: Throwable) {
            }

        })

        binding.ipaList.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(baseContext, CommentsActivity::class.java)
            intent.putExtra(DataStorage.putExtraKey, postList[i].id)
            startActivity(intent)
        }

    }
}