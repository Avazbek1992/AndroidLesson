package uz.invinsible.layouts.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class AlertDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_dialog_activity_layout)

        findViewById<Button>(R.id.show_alert_dialog_btn_id)
            .setOnClickListener {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Save file")
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
                alertDialog.setMessage("Do you want to save file ?")
                alertDialog.setPositiveButton("yes") { dialog: DialogInterface, id: Int ->
                    run {
                        Toast.makeText(this, "clicked yes", Toast.LENGTH_SHORT).show()
                    }
                }

                alertDialog.setNeutralButton("cancel") { dialog: DialogInterface, id: Int ->
                    run {
                        Toast.makeText(this, "clicked cancel", Toast.LENGTH_SHORT).show()
                    }
                }

                alertDialog.setNegativeButton("no"){
                    dialog: DialogInterface, id: Int->
                    run{
                        Toast.makeText(this, "clicked no", Toast.LENGTH_SHORT).show()
                    }
                }

                alertDialog.setCancelable(false)
                alertDialog.create().show()
            }
    }
}