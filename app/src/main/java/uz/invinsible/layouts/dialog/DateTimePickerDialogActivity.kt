package uz.invinsible.layouts.dialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R
import kotlin.math.min

class DateTimePickerDialogActivity : AppCompatActivity(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_time_picker_layout)

        findViewById<Button>(R.id.date_time_picker_id)
            .setOnClickListener {
                val calendar: Calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog =
                    DatePickerDialog(
                        this,
                        this,
                        year,
                        month,
                        day
                    )

                datePickerDialog.show()
            }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, day: Int) {
        findViewById<TextView>(R.id.date_time_year_id).text = "Year: $year"
        findViewById<TextView>(R.id.date_time_month_id).text = "Month: ${month + 1}"
        findViewById<TextView>(R.id.date_time_day_id).text = "Day: $day"

        val calendar: Calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            this,
            this,
            hour,
            minute,
            true
        )

        timePickerDialog.show()

    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(timePicker: TimePicker?, hour: Int, minute: Int) {
        findViewById<TextView>(R.id.date_time_hour_id).text = "Hour: $hour"
        findViewById<TextView>(R.id.date_time_minute_id).text = "Minute: $minute"
    }
}