package uz.invinsible.layouts.dialog

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class MyDialogFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_dialog_activity_layout)
        findViewById<Button>(R.id.show_alert_dialog_btn_id)
            .setOnClickListener {
                val myDialogFragment = MyDialogFragment()
                myDialogFragment.show(supportFragmentManager, "Dialog")
            }
    }

    override fun onBackPressed() {
        Toast.makeText(this, "onBack pressed", Toast.LENGTH_SHORT).show()
        super.onBackPressed()
    }
}