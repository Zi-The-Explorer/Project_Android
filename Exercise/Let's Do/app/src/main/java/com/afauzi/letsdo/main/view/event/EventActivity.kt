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
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.bottom_sheet_layout_event.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class EventActivity : AppCompatActivity() {

    private lateinit var listItemEventArrayList: ArrayList<ModelItemEvent>
    private lateinit var recyclerViewItemEvent: ShimmerRecyclerView

    private lateinit var calendar: Calendar
    private lateinit var simpleDateFormat: SimpleDateFormat

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        firebaseDatabase = FirebaseDatabase.getInstance()

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!

        readDataCategory(tv_category_name, "data")

        val bundle = intent.extras
        val dataString = bundle!!.getString("data")
        tv_data_event_empty.text = "Untuk category $dataString masih belum ada acara nih"

        EventArrowUp.setOnClickListener {
            slideAnimation(R.anim.slide_up_animation, ll_event_done_to_animation)
            EventArrowUp.visibility = View.GONE
            EventArrowDown.visibility = View.VISIBLE
        }

        EventArrowDown.setOnClickListener {
          slideAnimation(R.anim.slide_down_animation, ll_event_done_to_animation)
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

    private fun slideAnimation(anim: Int, viewTarget: View) {
        val animation = AnimationUtils.loadAnimation(this, anim)
        viewTarget.startAnimation(animation)
    }

    private fun getListItemEvent() {

        val bundle = intent.extras
        val categoryToken = bundle!!.getString("token_task")

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        val userId = user.uid

        databaseReference =
            firebaseDatabase.getReference("user_detail").child(userId).child("event_task")
                .child(categoryToken!!)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    listItemEventArrayList.clear()

                    for (listItemEvent in snapshot.children) {
                        val listItem = listItemEvent.getValue(ModelItemEvent::class.java)
                        listItemEventArrayList.add(listItem!!)
                    }
                    tv_data_event_empty.visibility = View.GONE
                    rv_list_event_item.visibility = View.VISIBLE
                    recyclerViewItemEvent.adapter = AdapterListItemEvent(listItemEventArrayList)
                } else {
                    tv_data_event_empty.visibility = View.VISIBLE
                    rv_list_event_item.visibility = View.GONE
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
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout_event, null)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismissEvent)
        val btnCalenderEvent = view.findViewById<Button>(R.id.btn_calendar_event)
        val titleCategoryBottomSheet = view.findViewById<TextView>(R.id.tv_title_bottom_sheet_event)

        val btnSendEvent = view.findViewById<Button>(R.id.idBtnSendEvent)

        readDataCategory(titleCategoryBottomSheet, "data")

        btnCalenderEvent.setOnClickListener {
            datePicker()
        }

        btnSendEvent.setOnClickListener {

            val etCreateEvent = view.et_send_new_event.text.toString().trim()
            val etCreateDesc = view.et_send_desc_event.text.toString().trim()

            user = auth.currentUser!!
            val uid = user.uid
            val bundle = intent.extras
            val categoryToken = bundle!!.getString("token_task")

            calendar = Calendar.getInstance()
            simpleDateFormat = SimpleDateFormat("EEEE | dd-MM-yyyy | h:mm:ss a")
            val date: String = simpleDateFormat.format(calendar.time)

            databaseReference = firebaseDatabase.getReference("user_detail").child(uid).child("event_task").child(categoryToken!!).child(etCreateEvent)
            val hashMap: HashMap<String, String> = HashMap()
            hashMap["category_id"] = categoryToken
            hashMap["event_name"] = etCreateEvent
            hashMap["desc"] = etCreateDesc
            hashMap["item_date_created"] = date

            if (etCreateEvent.isEmpty() && etCreateDesc.isEmpty()) {
                Toast.makeText(this, "Task is required!", Toast.LENGTH_SHORT).show()
            } else {
                databaseReference.setValue(hashMap).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Succes create a new task", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(this, "Ada Masalah Jaringan", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }

    private fun readDataCategory(textView: TextView? = null, key: String) {
        if (intent.extras != null) {
            val bundle = intent.extras
            textView!!.text = bundle!!.getString(key)
        } else {
            textView!!.text = intent.getStringExtra(key)
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