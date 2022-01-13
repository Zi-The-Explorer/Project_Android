package com.afauzi.letsdo.api.dao

import androidx.room.*
import com.afauzi.letsdo.data.Note

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * from notes")
    fun getAll(): List<Note>

    @Query("SELECT * from notes where id = :id")
    fun getById(id: Int): List<Note>

}