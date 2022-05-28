package com.afauzi.letsdo.main.view.event

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.afauzi.letsdo.R
import com.afauzi.letsdo.data.ModelItemEvent
import com.afauzi.letsdo.data.ModelItemEventClear
import com.afauzi.letsdo.databinding.ActivityEventBinding
import com.afauzi.letsdo.repo.AdapterListItemEvent
import com.afauzi.letsdo.repo.AdapterListItemEventClear
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.Inflater
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class EventActivity : AppCompatActivity(), AdapterListItemEvent.CallClickListenerEvent {

    private lateinit var binding: ActivityEventBinding

    private lateinit var listItemEventArrayList: ArrayList<ModelItemEvent>
    private lateinit var recyclerViewItemEvent: ShimmerRecyclerView

    private lateinit var recyclerViewItemEventClear: ShimmerRecyclerView
    private lateinit var listItemEventClearArrayList: ArrayList<ModelItemEventClear>

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!

        readDataCategory(binding.tvCategoryName, "data")

        val bundle = intent.extras
        val dataString = bundle!!.getString("data")
        binding.tvDataEventEmpty.text = "Untuk category $dataString masih belum ada acara nih"

        recyclerViewSetup()
        listItemEventArrayList = ArrayList()
        getListItemEvent()

        recyclerViewSetupClear()
        listItemEventClearArrayList = ArrayList()
        getListItemEventClear()

        getCountDataDone()

    }

    override fun onStart() {
        super.onStart()

        binding.EventArrowUp.setOnClickListener {
            slideAnimation(R.anim.slide_up_animation, binding.llEventDoneToAnimation)
            binding.EventArrowUp.visibility = View.GONE
            binding.EventArrowDown.visibility = View.VISIBLE
        }

        binding.EventArrowDown.setOnClickListener {
            slideAnimation(R.anim.slide_down_animation, binding.llEventDoneToAnimation)
            binding.EventArrowUp.visibility = View.VISIBLE
            binding.EventArrowDown.visibility = View.GONE
        }

        binding.EventArrowBack.setOnClickListener {
            super.onBackPressed()
        }

        binding.BtnMoreOption.setOnClickListener {
            popupMenu()
        }

        binding.floatingActionButtonCreateEvent.setOnClickListener {
            bottomSheetDialog()
        }
    }

    private fun getCountDataDone() {
        val bundle = intent.extras
        val categoryToken = bundle!!.getString("token_task")

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        val userId = user.uid

        databaseReference =
            firebaseDatabase.getReference("user_detail").child(userId).child("_delete_event_task")
                .child(categoryToken!!)
        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                val size: String = snapshot.childrenCount.toString()
                binding.tvDoneEventCount.text = "Done($size)"
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

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
                    binding.tvDataEventEmpty.visibility = View.GONE
                    binding.rvListEventItem.visibility = View.VISIBLE
                    recyclerViewItemEvent.adapter = AdapterListItemEvent(
                        this@EventActivity,
                        this@EventActivity,
                        listItemEventArrayList
                    )
                } else {
                    binding.tvDataEventEmpty.visibility = View.VISIBLE
                    binding.rvListEventItem.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getListItemEventClear() {
        val bundle = intent.extras
        val categoryToken = bundle!!.getString("token_task")

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        val userId = user.uid

        databaseReference =
            firebaseDatabase.getReference("user_detail").child(userId).child("_delete_event_task")
                .child(categoryToken!!)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    listItemEventClearArrayList.clear()

                    binding.tvDataEventClearEmpty.visibility = View.GONE
                    binding.rvListEventItemClear.visibility = View.VISIBLE

                    for (listItemEventClear in snapshot.children) {
                        val listItem = listItemEventClear.getValue(ModelItemEventClear::class.java)
                        listItemEventClearArrayList.add(listItem!!)
                    }
                    recyclerViewItemEventClear.adapter = AdapterListItemEventClear(
                        listItemEventClearArrayList
                    )
                } else {
                    binding.tvDataEventClearEmpty.visibility = View.VISIBLE
                    binding.rvListEventItemClear.visibility = View.GONE
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

    private fun recyclerViewSetupClear() {
        recyclerViewItemEventClear = findViewById(R.id.rv_list_event_item_clear)
        recyclerViewItemEventClear.setHasFixedSize(true)
        recyclerViewItemEventClear.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun datePicker(setDate: TextView) {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setTheme(R.style.ThemeOverlay_App_DatePicker)
                .build()
        datePicker.addOnPositiveButtonClickListener {
            val c = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
            val text = c.format(it)
            setDate.text = text

        }
        datePicker.show(supportFragmentManager, "tag")
    }

    private fun timePicker(setTime: TextView) {

        val isSystem24Hour = is24HourFormat(this)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select Appointment time")
                .setInputMode(INPUT_MODE_KEYBOARD)
                .setTheme(R.style.ThemeOverlay_App_TimePicker)
                .build()
        picker.addOnPositiveButtonClickListener {
            val h = picker.hour
            val m = picker.hour
            val time = "$h:$m"

            setTime.text = time
        }
        picker.show(supportFragmentManager, "tag")

    }

    @SuppressLint("InflateParams")
    private fun bottomSheetDialog() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout_event, null)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismissEvent)
        val btnCalenderEvent = view.findViewById<Button>(R.id.btn_calendar_event)
        val btnTimeEvent = view.findViewById<Button>(R.id.btn_time_event)
        val titleCategoryBottomSheet = view.findViewById<TextView>(R.id.tv_title_bottom_sheet_event)

        val textDate = view.findViewById<TextView>(R.id.item_event_select_date)
        val textTime = view.findViewById<TextView>(R.id.item_event_select_time)

        val btnSendEvent = view.findViewById<Button>(R.id.idBtnSendEvent)

        readDataCategory(titleCategoryBottomSheet, "data")

        btnTimeEvent.setOnClickListener {
            timePicker(textTime)
        }

        btnCalenderEvent.setOnClickListener {
            datePicker(textDate)
        }

        btnSendEvent.setOnClickListener {

            val etCreateEvent = view.findViewById<EditText>(R.id.et_send_new_event).text.toString().trim()
            val etCreateDesc = view.findViewById<EditText>(R.id.et_send_desc_event).text.toString().trim()

            val tvSelectDate = view.findViewById<TextView>(R.id.item_event_select_date).text.toString()
            val tvSelectTime = view.findViewById<TextView>(R.id.item_event_select_time).text.toString()

            user = auth.currentUser!!
            val uid = user.uid
            val bundle = intent.extras
            val categoryToken = bundle!!.getString("token_task")

            databaseReference =
                firebaseDatabase.getReference("user_detail").child(uid).child("event_task")
                    .child(categoryToken!!).child(etCreateEvent)
            val hashMap: HashMap<String, String> = HashMap()
            hashMap["category_id"] = categoryToken
            hashMap["event_name"] = etCreateEvent
            hashMap["desc"] = etCreateDesc
            hashMap["time"] = "$tvSelectDate | $tvSelectTime"
            hashMap["item_date_created"] = tvSelectDate
            hashMap["item_time_created"] = tvSelectTime

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

        val popupMenu = PopupMenu(this, binding.BtnMoreOption, Gravity.RIGHT)
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
        val dialogView: View = layoutInflater.inflate(R.layout.no_page_introduction, null)
        dialog.setView(dialogView)
        dialog.setTitle(title)
        dialog.show()
    }

    override fun onClickListenerEvent(data: ModelItemEvent) {

        user = auth.currentUser!!
        val uid = user.uid
        val bundle = intent.extras
        val categoryToken = bundle!!.getString("token_task")

        databaseReference =
            firebaseDatabase.getReference("user_detail").child(uid).child("_delete_event_task")
                .child(categoryToken!!).child(data.event_name!!)
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["category_id"] = categoryToken
        hashMap["item_name"] = data.event_name!!

            databaseReference.setValue(hashMap).addOnCompleteListener {
                if (it.isSuccessful) {

                    databaseReference =
                        firebaseDatabase.getReference("user_detail").child(uid).child("event_task")
                            .child(categoryToken).child(data.event_name!!)
                    databaseReference.removeValue()

                    Toast.makeText(this, "${data.event_name} Selesai", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ada Masalah Jaringan", Toast.LENGTH_SHORT).show()
                }
            }
    }
}