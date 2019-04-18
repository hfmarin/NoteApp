package com.henrymarin.noteapp

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class NoteRepository(application: Application) {

    var noteDao: NoteDao? = null
    var allNotes: LiveData<List<Note>>? = null

    init {
        val noteDatabase = NoteDatabase.getInstance(application)
        noteDao = noteDatabase?.NoteDao()
        allNotes = noteDao?.getAllNotes()
    }

    @WorkerThread
    suspend fun insert(note: Note){
        noteDao?.insert(note)
    }

    @WorkerThread
    suspend fun update(note: Note) {
        noteDao?.update(note)
    }

    @WorkerThread
    suspend fun delete(note: Note) {
        noteDao?.delete(note)
    }

    @WorkerThread
    suspend fun deleteAllNote() {
        noteDao?.getAllNotes()
    }

}