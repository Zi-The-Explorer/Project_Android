package com.afauzi.letsdo.main.view.event

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.afauzi.letsdo.R
import com.afauzi.letsdo.data.ModelItemEvent
import com.afauzi.letsdo.repo.AdapterListItemEvent
import com.airbnb.lottie.LottieAnimationView
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_event.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventActivity : AppCompatActivity() {

    private lateinit var listItemEventArrayList: ArrayList<ModelItemEvent>
    private lateinit var recyclerViewItemEvent: ShimmerRecyclerView

    private lateinit var calendar: Calendar
    private lateinit var simpleDateFormat: SimpleDateFormat

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        firebaseDatabase = FirebaseDatabase.getInstance()

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!

        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("EEEE | dd-MM-yyyy | h:mm:ss a")
        val date: String = simpleDateFormat.format(calendar.time)



        readDataCategory()

        EventArrowUp.setOnClickListener {
            val animationUp = AnimationUtils.loadAnimation(this, R.anim.slide_up_animation)
            ll_event_done_to_animation.startAnimation(animationUp)
            EventArrowUp.visibility = View.GONE
            EventArrowDown.visibility = View.VISIBLE
        }
        EventArrowDown.setOnClickListener {
            val animationDown = AnimationUtils.loadAnimation(this, R.anim.slide_down_animation)
            ll_event_done_to_animation.startAnimation(animationDown)
            EventArrowUp.visibility = View.VISIBLE
            EventArrowDown.visibility = View.GONE
        }

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


        recyclerViewSetup()
        listItemEventArrayList = ArrayList<ModelItemEvent>()
        getListItemEvent()



    }

    private fun getListItemEvent() {
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        val userId = user.uid

        databaseReference = firebaseDatabase.getReference("user_detail").child(userId).child("item_task_event").child("Tugas 3")
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (listItemEvent in snapshot.children) {
                        val listItem = listItemEvent.getValue(ModelItemEvent::class.java)
                        listItemEventArrayList.add(listItem!!)
                    }
                    recyclerViewItemEvent.adapter = AdapterListItemEvent(listItemEventArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun recyclerViewSetup() {
        recyclerViewItemEvent = findViewById(R.id.rv_list_event_item)
        recyclerViewItemEvent.setHasFixedSize(true)
        recyclerViewItemEvent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun strikeThrough(textView: TextView) {
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

    @SuppressLint("InflateParams")
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
            btnSendTask.visibility = View.GONE
            animationLoading.visibility = View.VISIBLE

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

    @SuppressLint("RtlHardcoded")
    private fun popupMenu() {

        val popupMenu = PopupMenu(this, BtnMoreOption, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.event_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            dialogShowNotPage(it.title)
//            when (it.itemId) {
//                R.id.short_by -> {
//                    Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
//                }
//            }

            true
        }
        popupMenu.show()
    }

    private fun dialogShowNotPage(title: CharSequence) {
        val dialog = MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_rounded)
        dialog.setView(R.layout.no_page_introduction)
        dialog.setTitle(title)
        dialog.show()
    }
}