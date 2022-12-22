package uz.invinsible.layouts.locale

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import uz.invinsible.layouts.R
import uz.invinsible.layouts.databinding.LocaleActivityLayoutBinding
import uz.invinsible.layouts.shared_pref.Activity
import uz.invinsible.layouts.shared_pref.DataStorage
import java.util.*

class LocaleActivity : AppCompatActivity(), OnClickListener {
    lateinit var binding: LocaleActivityLayoutBinding
    lateinit var storage: DataStorage
    override fun onCreate(savedInstanceState: Bundle?) {
//        val configuration = Configuration()
//        when (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
//            Configuration.UI_MODE_NIGHT_NO -> {
//                setTheme(com.google.android.material.R.style.Theme_AppCompat_DayNight)
//            }
//            Configuration.UI_MODE_NIGHT_YES -> {
//                setTheme(com.google.android.material.R.style.Theme_AppCompat_Light)
//            }
//        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()

        storage = DataStorage(baseContext)
        setLocale(baseContext, storage.readSharePref("locale"))
        super.onCreate(savedInstanceState)
        binding = LocaleActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val locale = Locale("en-rGB")
//        Locale.setDefault(locale)
//        val configuration = Configuration()
//        configuration.setLocale(locale)
//        resources.updateConfiguration(configuration, resources.displayMetrics)

        binding.localeEn.setOnClickListener {
            startActivity(Intent(this, LocaleActivity::class.java))
            saveLocale("en")
            finish()
        }

        binding.localeRu.setOnClickListener {
            startActivity(Intent(this, LocaleActivity::class.java))
            saveLocale("sah")
            finish()
        }

        binding.localeKr.setOnClickListener {
            startActivity(Intent(this, LocaleActivity::class.java))
            saveLocale("uz")
            finish()
        }

        binding.localeUz.setOnClickListener {
            startActivity(Intent(this, LocaleActivity::class.java))
            saveLocale("ug")
            finish()
        }
    }

    private fun saveLocale(localeCode: String) {
        storage.saveLocale(localeCode)
    }

    private fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = context.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onClick(p0: View?) {
        when (p0?.rootView) {
            binding.localeEn -> {
            }
        }
    }

}
