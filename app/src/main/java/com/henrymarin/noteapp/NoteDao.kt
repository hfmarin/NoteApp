package com.henrymarin.noteapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note")
    fun deleteAllNotes()

    @Query("SELECT * FROM note ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>
}