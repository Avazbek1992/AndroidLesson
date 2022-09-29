package uz.invinsible.layouts.spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R
import uz.invinsible.layouts.listview.CustomAdapter
import uz.invinsible.layouts.listview.UserModel

class SpinnerActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var names = arrayOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spinner_layout)

//        val items = resources.getStringArray(R.array.spinner_items)
        val spinner = findViewById<Spinner>(R.id.spinner_id)
//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items)
//        spinner.adapter = adapter

        names = arrayOf(
            "Azizbek",
            "Muhammadaziz",
            "Doniyor",
            "Nuriddin",
            "Abubakr",
            "Umidjon",
            "Shahriyor",
            "Umarjon",
            "Abubakr",
            "Jobirxon",
            "Azizbek",
            "Muhammadaziz",
            "Doniyor",
            "Nuriddin",
            "Abubakr",
            "Umidjon",
            "Shahriyor",
            "Umarjon",
            "Abubakr",
            "Jobirxon",
        )
        val subtitle = arrayOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
        )
        val image = arrayOf(
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
        )

        val arrayList = ArrayList<UserModel>()
        for (i in names.indices) {
            arrayList.add(
                UserModel(
                    names[i],
                    image[i],
                    subtitle[i]
                )
            )
        }

        val adapter = CustomAdapter(this, arrayList)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        Toast.makeText(this, names[position], Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}