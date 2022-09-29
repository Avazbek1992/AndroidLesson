package uz.invinsible.layouts.expandable_list_view

import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class ExpandableListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expandable_list_view_layout)

        val groupName = listOf("Chevrolet", "Tesla", "Mercedes", "BMW")

        val chevrolet = listOf("Tracker", "Tahoe", "Equinox", "Trailblazer")
        val tesla = listOf("Model S", "Model L", "Model 3", "Cyber cup")
        val mercedes = listOf("MayBach", "GLS", "ClassC", "IMG")
        val bmv = listOf("", "", "", "")

        var map: HashMap<String, String>

        val groupList = ArrayList<Map<String, String>>()

        for (group in groupName) {
            map = HashMap()
            map["group"] = group
            groupList.add(map)
        }

        val childData = ArrayList<ArrayList<Map<String, String>>>()
        var childItemData = ArrayList<Map<String, String>>()

        for (item in chevrolet) {
            map = HashMap()
            map["item"] = item
            childItemData.add(map)
        }
        childData.add(childItemData)
        childItemData = ArrayList()

        for (item in tesla) {
            map = HashMap()
            map["item"] = item
            childItemData.add(map)
        }
        childData.add(childItemData)
        childItemData = ArrayList()

        for (item in mercedes) {
            map = HashMap()
            map["item"] = item
            childItemData.add(map)
        }
        childData.add(childItemData)
        childItemData = ArrayList()

        for (item in bmv) {
            map = HashMap()
            map["item"] = item
            childItemData.add(map)
        }
        childData.add(childItemData)

        val expandableListView = findViewById<ExpandableListView>(R.id.expandable_list_view_id)
        val adapter = SimpleExpandableListAdapter(
            this,
            groupList,
            android.R.layout.simple_expandable_list_item_1,
            arrayOf("group"),
            intArrayOf(android.R.id.text1),
            childData,
            android.R.layout.simple_list_item_1,
            arrayOf("item"),
            intArrayOf(android.R.id.text1)
        )
        expandableListView.setAdapter(adapter)
    }
}