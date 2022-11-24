package uz.invinsible.layouts.api

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.invinsible.layouts.api.model.comments.CommentsItem
import uz.invinsible.layouts.shared_pref.DataStorage

class CommentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                arrayList = response.body() as ArrayList<CommentsItem>

                for (i in 0 until arrayList.size) {
                    if (arrayList[i].postId == id) {
                        println("id = ${arrayList[i].id}")
                        println("postId = ${arrayList[i].postId}")
                        println("name = ${arrayList[i].name}")
                        println("email = ${arrayList[i].email}")
                        println("body = ${arrayList[i].body}")
                    }
                }
//                val adapter = ApiListAdapter(arrayList)
//                binding.ipaList.adapter = adapter
            }

            override fun onFailure(call: Call<List<CommentsItem>>, t: Throwable) {
                Toast.makeText(baseContext, "Muvaffaqiyatsiz: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })



//        binding.ipaList.setOnItemClickListener { adapterView, view, position, l ->
//            val intent = Intent(baseContext, CommentsActivity::class.java)
//            intent.putExtra(DataStorage.putExtraKey, arrayList[position].userId)
//            startActivity(intent)
//        }
    }
}