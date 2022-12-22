package uz.invinsible.layouts.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.databinding.NoticationActivityLayoutBinding

class NotificationActivity : AppCompatActivity() {
    lateinit var binding: NoticationActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NoticationActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
