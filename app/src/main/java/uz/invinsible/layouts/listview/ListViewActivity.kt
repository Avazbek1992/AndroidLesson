package uz.invinsible.layouts.listview

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view_layout)

        val names = arrayOf(
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
        val listView = findViewById<ListView>(R.id.list_view_id)
        listView.adapter = adapter

//        val listView = findViewById<ListView>(R.id.list_view_id)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
//        listView.adapter = adapter
//
        listView.setOnItemClickListener { adapterView, view, position, l ->
            Toast.makeText(this, "${names[position]} ${subtitle[position]}", Toast.LENGTH_SHORT).show()
        }

    }
}