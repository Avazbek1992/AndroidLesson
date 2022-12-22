package uz.invinsible.layouts.bottom_nav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.invinsible.layouts.R

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_bottom_nav)

//        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_id)
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.bottom_nav_container_id, HomeFragment())
//            .commit()
//
//        bottomNavigationView.setOnItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.bottom_nav_menu_home_id -> {
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.bottom_nav_container_id, HomeFragment())
//                        .commit()
//                }
//                R.id.bottom_nav_menu_search_id -> {
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.bottom_nav_container_id, SearchFragment())
//                        .commit()
//                }
//                R.id.bottom_nav_menu_setting_id -> {
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.bottom_nav_container_id, SettingsFragment())
//                        .commit()
//                }
//            }
//
//            return@setOnItemSelectedListener true
//        }
    }
}