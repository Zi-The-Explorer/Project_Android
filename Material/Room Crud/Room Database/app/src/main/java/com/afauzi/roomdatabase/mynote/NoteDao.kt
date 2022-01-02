package com.afauzi.roomdatabase.mynote

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)
}