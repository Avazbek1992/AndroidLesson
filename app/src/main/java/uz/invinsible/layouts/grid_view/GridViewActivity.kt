package uz.invinsible.layouts.grid_view

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class GridViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_view_layout)

//        val names = arrayOf(
//            "Azizbek",
//            "Muhammadaziz",
//            "Doniyor",
//            "Nuriddin",
//            "Abubakr",
//            "Umidjon",
//            "Shahriyor",
//            "Umarjon",
//            "Abubakr",
//            "Jobirxon",
//            "Azizbek",
//            "Muhammadaziz",
//            "Doniyor",
//            "Nuriddin",
//            "Abubakr",
//            "Umidjon",
//            "Shahriyor",
//            "Umarjon",
//            "Abubakr",
//            "Jobirxon",
//        )
//
//        val gridView = findViewById<GridView>(R.id.grid_view_id)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
//        gridView.adapter = adapter


        val title = arrayOf(
                "Ronaldo",
                "Beckham",
                "Ronaldo",
                "Beckham",
                "Ronaldo",
                "Beckham",
                "Ronaldo",
                "Beckham",
                "Ronaldo",
                "Beckham",
                "Ronaldo",
                "Beckham",
                "Ronaldo",
                "Beckham",
                "Ronaldo",
                "Beckham",
                "Ronaldo",
                "Beckham",
                "Ronaldo",
                "Beckham",
                )

        val image = arrayOf(
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
            R.drawable.ic_ronaldo,
            R.drawable.ic_beckham,
        )

        val arrayList = ArrayList<GridModel>()
        for (i in title.indices){
            arrayList.add(GridModel(
                title[i],
                image[i]
            ))
        }


        val gridView = findViewById<GridView>(R.id.grid_view_id)
        val adapter = GridViewCustomAdapter(this, arrayList)
        gridView.adapter = adapter

    }
}