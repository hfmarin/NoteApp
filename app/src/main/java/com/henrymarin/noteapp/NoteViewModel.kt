package com.henrymarin.noteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var noteRepository: NoteRepository = NoteRepository(application)
    var notesList: LiveData<List<Note>>?

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        notesList = noteRepository.allNotes
    }

    fun insert(note: Note) = scope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }

    fun update(note: Note) = scope.launch(Dispatchers.IO) {
        noteRepository.update(note)
    }

    fun delete(note: Note) = scope.launch(Dispatchers.IO) {
        noteRepository.delete(note)
    }

    fun deleteAll() = scope.launch(Dispatchers.IO) {
        noteRepository.deleteAllNote()
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}