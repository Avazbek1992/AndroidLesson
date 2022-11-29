package uz.invinsible.layouts.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.invinsible.layouts.api.adapters.TodoAdapter
import uz.invinsible.layouts.api.model.todos.TodosItem
import uz.invinsible.layouts.databinding.ApiTodoLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage

class ApiToDoActivity: AppCompatActivity() {
    lateinit var binding:ApiTodoLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ApiTodoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var todoList= ArrayList<TodosItem>()
        val client = Client()
        client.getRetrofit().getTodos()
            .enqueue( object : Callback<List<TodosItem>>{
                override fun onResponse(
                    call: Call<List<TodosItem>>,
                    response: Response<List<TodosItem>>
                ) {
                    if (response.isSuccessful){
                        todoList = response.body() as ArrayList<TodosItem>
                        val adapter = TodoAdapter(todoList, intent.getIntExtra(DataStorage.putExtraKey, 0))
                        binding.todoListView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<List<TodosItem>>, t: Throwable) {
                }

            })

    }
}