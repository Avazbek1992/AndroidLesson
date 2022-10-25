package uz.invinsible.layouts.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import uz.invinsible.layouts.R

class SnackbarActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snackbar_activity_layout)

//        Default snackbar

//        findViewById<Button>(R.id.snack_open_btn_id)
//            .setOnClickListener {
//                val snackbar = Snackbar.make(it, "This is Snackbar", Snackbar.LENGTH_LONG)
//                snackbar.setActionTextColor(Color.RED)
//                snackbar.setBackgroundTint(Color.CYAN)
//                snackbar.setTextColor(Color.BLUE)
//                snackbar.setAction("UNDO"){
//                    Toast.makeText(baseContext, "UNDO clicked...", Toast.LENGTH_SHORT).show()
//                }
//                snackbar.show()
//            }

        //        Custom snackbar

        findViewById<Button>(R.id.snack_open_btn_id)
            .setOnClickListener {
                val snackbar = Snackbar.make(it, "", Snackbar.LENGTH_INDEFINITE)
                val root = layoutInflater.inflate(R.layout.snackbar_custom_layout, null)
                val snackbarLayout = snackbar.view as SnackbarLayout
                snackbarLayout.addView(root)
                snackbar.view.setBackgroundColor(Color.TRANSPARENT)
                snackbar.show()

                val undoBtn: TextView = root.findViewById(R.id.snackbar_undo_id)
                val message: TextView = root.findViewById(R.id.snackbar_message_id)
                val counter: TextView = root.findViewById(R.id.snackbar_counter_id)

                var count = 5
                counter.text = count.toString()
                message.text = "Aru you sure?"

                Thread {
                    while (count > 0) {
                        Thread.sleep(1000)
                        runOnUiThread {
                            count--
                            counter.text = count.toString()
                        }
                    }

                    snackbar.dismiss()
                }.start()

                undoBtn.setOnClickListener {
                    Toast.makeText(baseContext, "UNDO clicked...", Toast.LENGTH_SHORT).show()
                }
            }
    }
}