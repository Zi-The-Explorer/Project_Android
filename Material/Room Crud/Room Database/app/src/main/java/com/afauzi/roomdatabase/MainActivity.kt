package com.afauzi.roomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.afauzi.roomdatabase.mynote.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    companion object {
        val CREATE_NOTE_REQUEST_CODDE = 1
    }

    private lateinit var model: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProvider(this).get(NoteViewModel::class.java)

        btn_to_create.setOnClickListener {
           Intent(this@MainActivity, CreateNoteActivity::class.java).also {
               startActivityForResult(it, CREATE_NOTE_REQUEST_CODDE)
           }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NOTE_REQUEST_CODDE && resultCode == Activity.RESULT_OK) {
            val note = data?.getStringExtra(CreateNoteActivity.NEW_NOTE)
            if (note != null) {
                model.insert(note)
            }
            Toast.makeText(this, "Note Brhasil ditambahkan", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, "Note Tidak Ditambahkan", Toast.LENGTH_SHORT).show() 
        }
    }
}