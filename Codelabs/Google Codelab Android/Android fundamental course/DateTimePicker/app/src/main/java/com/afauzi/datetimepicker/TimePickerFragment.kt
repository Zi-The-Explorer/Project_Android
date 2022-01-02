package com.afauzi.datetimepicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.*

@Suppress("UNREACHABLE_CODE")
class TimePickerFragment : DialogFragment (), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)

        // Use the current date as the default date in the picker
        val calendar: Calendar = Calendar.getInstance()

        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)

        // Create a new instance of DatePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, true )

        Log.d("TimePickerFragment: ", calendar.toString())

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

    }


}