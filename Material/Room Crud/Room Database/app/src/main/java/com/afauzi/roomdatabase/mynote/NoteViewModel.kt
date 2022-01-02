package com.afauzi.roomdatabase.mynote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private var noteDao: NoteDao
    private var noteDb: NoteRoomDatabase

    init {
        noteDb = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDb.NoteDao()
    }

    fun insert(textNote: String) {
        insertToDatabase(textNote)
    }

    private fun insertToDatabase(textNote: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val noteId = UUID.randomUUID().toString()
            val note = Note(
                noteId,
                textNote
            )
            noteDao.insert(note)
        }
    }
}