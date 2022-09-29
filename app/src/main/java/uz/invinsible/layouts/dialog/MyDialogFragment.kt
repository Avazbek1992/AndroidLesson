package uz.invinsible.layouts.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.setTitle("Save file")
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
        alertDialog.setMessage("Do you want to save file ?")
        alertDialog.setPositiveButton("yes") { dialog: DialogInterface, id: Int ->
            run {
                Toast.makeText(requireActivity(), "clicked yes", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.setNeutralButton("cancel") { dialog: DialogInterface, id: Int ->
            run {
                Toast.makeText(requireActivity(), "clicked cancel", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.setNegativeButton("no") { dialog: DialogInterface, id: Int ->
            run {
                Toast.makeText(requireActivity(), "clicked no", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.setCancelable(false)
        return alertDialog.create()
    }
}