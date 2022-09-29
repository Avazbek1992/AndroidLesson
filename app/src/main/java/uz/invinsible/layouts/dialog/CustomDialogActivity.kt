package uz.invinsible.layouts.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class CustomDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_activity_layout)

        findViewById<Button>(R.id.show_custom_dialog_btn_id)
            .setOnClickListener {
                val customDialog = Dialog(this)
                customDialog.setContentView(R.layout.custom_dialog_layout)
                customDialog.show()
                val okBtn = customDialog.findViewById<TextView>(R.id.dialog_ok_id)
                val check1 = customDialog.findViewById<CheckBox>(R.id.dialog_check1)
                val check2 = customDialog.findViewById<CheckBox>(R.id.dialog_check2)
                val check3 = customDialog.findViewById<CheckBox>(R.id.dialog_check3)
                val check4 = customDialog.findViewById<CheckBox>(R.id.dialog_check4)
                okBtn.setOnClickListener {
                    var result = ""
                    if (check1.isChecked) {
                        result = result.plus("${check1.text}, ")
                    }
                    if (check2.isChecked) {
                        result = result.plus("${check2.text}, ")
                    }
                    if (check3.isChecked) {
                        result = result.plus("${check3.text}, ")
                    }
                    if (check4.isChecked) {
                        result = result.plus("${check4.text}, ")
                    }

                    findViewById<TextView>(R.id.custom_dialog_tv_id)
                        .text = result
                    customDialog.hide()
                }
            }
    }
}