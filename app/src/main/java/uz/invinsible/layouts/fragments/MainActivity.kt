package uz.invinsible.layouts.fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main_activity_layout)

        val fragment = MyFragment();
        val bundle = Bundle()
        bundle.putString("key", "Fragment")
        fragment.arguments = bundle
        findViewById<Button>(R.id.fragment_btn_id).setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack("back_")
                .replace(R.id.container, fragment)
                .commit()
        }

        findViewById<Button>(R.id.fragment2_btn_id).setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack("back_")
                .replace(R.id.container, MyFragment2())
                .commit()
        }
    }
}