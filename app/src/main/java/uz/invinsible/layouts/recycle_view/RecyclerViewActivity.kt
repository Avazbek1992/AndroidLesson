package uz.invinsible.layouts.recycle_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R
import kotlin.collections.ArrayList
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity(), RecycleItemClick {
    private val random: Random = Random.Default
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycle_view_layout)

        val onItemClick: RecycleItemClick = this
        val fullNames = resources.getStringArray(R.array.fullNames)
        val phoneNumbers = resources.getStringArray(R.array.phoneNumbers)
        val colors = arrayOf(
            resources.getColor(R.color.purple_200),
            resources.getColor(R.color.gray),
            resources.getColor(R.color.red),
            resources.getColor(R.color.green),
            resources.getColor(R.color.purple_700),
            resources.getColor(R.color.purple_500),
            resources.getColor(R.color.yellow),
            resources.getColor(R.color.teal_700),
            resources.getColor(R.color.teal_200),
        )


        val arrayList = ArrayList<Contacts>()
        for (i in fullNames.indices) {
            val imgTxt = getIMGTxt(fullNames[i])
            arrayList.add(
                Contacts(
                    fullNames[i],
                    phoneNumbers[i],
                    imgTxt,
                    colors[random.nextInt(colors.size)]
                )
            )
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_id)
        val adapter = RecyclerCustomAdapter(/*onItemClick,*/ arrayList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun getIMGTxt(fullName: String): String {
        val txtArr = fullName.split(" ")
        return txtArr[0][0] + "" + txtArr[1][0]
    }

    override fun onItemClick(position: Int) {
        println(position)
    }
}

