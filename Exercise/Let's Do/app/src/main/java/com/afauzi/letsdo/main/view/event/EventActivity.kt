package com.afauzi.letsdo.main.view.event

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.afauzi.letsdo.R
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*

class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        readDataCategory()

        EventArrowBack.setOnClickListener {
            super.onBackPressed()
        }

        BtnMoreOption.setOnClickListener {
            popupMenu()
        }

        floating_action_button_create_event.setOnClickListener {
            bottomSheetDialog()
        }

        strikeThrough(tv_clear1)
        strikeThrough(tv_clear2)
    }

    fun strikeThrough(textView: TextView) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    private fun datePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.show(supportFragmentManager, "tag")
    }

    private fun bottomSheetDialog() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
        val btnSendTask = view.findViewById<Button>(R.id.idBtnSendTask)
        val btnCalenderEvent = view.findViewById<Button>(R.id.btn_calendar)

        btnCalenderEvent.setOnClickListener {
            datePicker()
        }

        btnSendTask.setOnClickListener {

            val animationLoading =
                view.findViewById<LottieAnimationView>(R.id.animationLoadingDialog)
            val animationSucces =
                view.findViewById<LottieAnimationView>(R.id.animationSuccessDialog)
            btnSendTask.visibility = View.GONE
            animationLoading.visibility = View.VISIBLE

            val etCreateNewTask = view.et_send_new_task.text.toString().trim()

            val auth = FirebaseAuth.getInstance()
            val user: FirebaseUser? = auth.currentUser
            val uid: String = user!!.uid
        }

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }

    private fun readDataCategory() {
        if (intent.extras != null) {
            val bundle = intent.extras
            tv_category_name.text = bundle!!.getString("data")
        } else {
            tv_category_name.text = intent.getStringExtra("data")
        }
    }

    private fun popupMenu() {
        val popupMenu = PopupMenu(this, BtnMoreOption, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.event_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.today -> {
                    Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        popupMenu.show()
    }
}