package uz.invinsible.layouts.api.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import uz.invinsible.layouts.api.model.todos.TodosItem
import uz.invinsible.layouts.databinding.ApiTodoItemBinding
import uz.invinsible.layouts.databinding.ApiTodoLayoutBinding

class TodoAdapter(private val todoList: ArrayList<TodosItem>, val userId: Int) : BaseAdapter() {
    lateinit var binding: ApiTodoItemBinding
    override fun getCount(): Int {
        return todoList.size
    }

    override fun getItem(p0: Int): Any {
        return todoList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        binding = ApiTodoItemBinding.inflate(LayoutInflater.from(p2?.context), p2, false)

        if (userId == todoList[p0].userId) {
            binding.todoTitle.text = todoList[p0].title
            binding.todoCheckBox.isChecked = todoList[p0].completed
        }

        return binding.root
    }
}