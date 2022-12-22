package uz.invinsible.layouts.others

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.invinsible.layouts.R
import java.io.File

class PicassoActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycle_view_layout)
        val arrayList=ArrayList<ItemModelTest>()
        val names= arrayOf("Azizbek","MuhammadAziz")
        val url= arrayOf("https://firebasestorage.googleapis.com/v0/b/androidlesson-6c60c.appspot.com/o/2.jpg?alt=media&token=3673c305-34cf-4c5c-b0c7-c4aceb6006e3","https://firebasestorage.googleapis.com/v0/b/androidlesson-6c60c.appspot.com/o/website.jpg?alt=media&token=f28b4762-9a9d-4ead-aa73-979bf310b0fb")
        for (i in names.indices){
            arrayList.add(
                ItemModelTest(
                names[i],url[i]
            )
            )
        }
        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view_id)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter= PicassoTestAdapter(arrayList)


        val f1 = File(
            Environment.getExternalStorageDirectory().toString() + "/" + "Telegram"
        )
        if (!f1.exists()) {
            f1.mkdirs()
        }
        println("PACKAGENAME: ${Environment.getDataDirectory()}")
        println("PACKAGENAME: ${Environment.getDownloadCacheDirectory()}")
        println("PACKAGENAME: ${Environment.getExternalStorageDirectory()}")
    }
}