package uz.invinsible.layouts.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import uz.invinsible.layouts.R

class BottomSheetDialog : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_sheet_activity_layout)
        val mBottomSheet = findViewById<LinearLayout>(R.id.my_boot_sheet_layout_id)
        val bottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet)
        bottomSheetBehavior.peekHeight = 100

        findViewById<Button>(R.id.open_id)
            .setOnClickListener {
//                N1
//                val bottomSheetDialogFragment = MyBottomSheetDialogFragment()
//                bottomSheetDialogFragment.show(supportFragmentManager, "This is bottom sheet")
                    bottomSheetBehavior.state  = BottomSheetBehavior.STATE_HIDDEN
                    bottomSheetBehavior.state  = BottomSheetBehavior.STATE_EXPANDED

//                N2
            }
    }
}