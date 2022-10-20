package uz.invinsible.layouts.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.invinsible.layouts.R

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.bottom_sheet_dialog_fragment, container, false)
        val okBtn: Button = root.findViewById(R.id.bottom_sheet_btn_id)
        okBtn.setOnClickListener {
            Toast.makeText(requireActivity(), "OK btn clicked", Toast.LENGTH_SHORT).show()
        }
        return root
    }
}