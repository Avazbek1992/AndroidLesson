package uz.invinsible.layouts.others

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.databinding.ScrollVeiwBinding

class ScrollViewActivity : AppCompatActivity() {
    lateinit var binding: ScrollVeiwBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScrollVeiwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lview.adapter = ScrollViewAdapter()

//        binding.rview.adapter = RecyclerCustomAdapter()
//        binding.rview.layoutManager = LinearLayoutManager(baseContext)
    }
}