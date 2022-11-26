package uz.invinsible.layouts.api

import android.icu.text.LocaleDisplayNames.UiListItem
import android.os.Bundle
import android.provider.Contacts.Intents.UI
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.internal.Storage
import uz.invinsible.layouts.api.model.users.UsersItem
import uz.invinsible.layouts.databinding.ProfileActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.DataStorage

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ProfileActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val array = intent.getStringArrayListExtra(DataStorage.putExtraKey)
        binding.userFullName.text = array?.get(0).toString()
        binding.profileAddress.text =array?.get(1).toString()
        binding.profilePhone.text = array?.get(2).toString()
        binding.profileEmail.text = array?.get(3).toString()
        binding.profileWebsite.text = array?.get(4).toString()
    }
}