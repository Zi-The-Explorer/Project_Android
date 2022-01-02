package com.afauzi.datetimepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showDatePickerDialog(view: View) {
        val newFragment: DialogFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, getString(R.string.date_picker))
        Log.d("Date Picker: ", newFragment.toString())
    }

    fun showTimePickerDialog(view: View) {
        val newFragment: DialogFragment = TimePickerFragment()
        newFragment.show(supportFragmentManager, getString(R.string.time_picker))
        Log.d("Time Picker: ", newFragment.toString())
    }
}