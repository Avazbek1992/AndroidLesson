package uz.invinsible.layouts.api

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import uz.invinsible.layouts.databinding.ProfileActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage

class ApiProfileActivity : AppCompatActivity() {
    lateinit var binding: ProfileActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val array = intent.getStringArrayListExtra(DataStorage.putExtraKey)
        Picasso.get().load(array?.get(0).toString()).into(binding.albumsId)
        binding.userFullName.text = array?.get(1).toString()
        binding.profileAddress.text =array?.get(2).toString()
        binding.profilePhone.text = array?.get(3).toString()
        binding.profileEmail.text = array?.get(4).toString()
        binding.profileWebsite.text = array?.get(5).toString()

        binding.profilePosts.setOnClickListener {
            val intent = Intent(this, ApiPostsActivity::class.java)
            intent.putExtra(DataStorage.putExtraKey, intent.getStringExtra(DataStorage.putExtraKey)?.toInt())
            startActivity(intent)
        }

        binding.profileTodos.setOnClickListener {
            val intent = Intent(this, ApiToDoActivity::class.java)
            intent.putExtra(DataStorage.putExtraKey, intent.getStringExtra(DataStorage.putExtraKey)?.toInt())
            startActivity(intent)
        }

    }
}