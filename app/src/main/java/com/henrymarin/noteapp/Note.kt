package com.henrymarin.noteapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var title: String,
    var description: String,
    var priority: Int?
) {

    constructor(title: String, description: String, priority: Int?) : this(null, title, description, priority)
}