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
import uz.invinsible.layouts.api.adapters.CommentAdapter
import uz.invinsible.layouts.api.model.comments.CommentsItem
import uz.invinsible.layouts.databinding.ApiCommentActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage

class CommentsActivity : AppCompatActivity() {
    lateinit var binding: ApiCommentActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ApiCommentActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra(DataStorage.putExtraKey, -1)
        var arrayList = ArrayList<CommentsItem>()
        val retrofitBuild = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuild.getComments()

        retrofitData.enqueue(object : Callback<List<CommentsItem>> {
            override fun onResponse(
                call: Call<List<CommentsItem>>,
                response: Response<List<CommentsItem>>
            ) {
                if (response.isSuccessful) {
                    arrayList.clear()
                    val list = response.body() as ArrayList<CommentsItem>
                    for (i in 0 until list.size) {
                        if (list[i].postId == id)
                            arrayList.add(list[i])
                    }
                    val adapter = CommentAdapter(arrayList)
                    binding.ipaCommentList.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<CommentsItem>>, t: Throwable) {
                Toast.makeText(baseContext, "Muvaffaqiyatsiz: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}