package com.henrymarin.noteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class AddNoteActivity : AppCompatActivity() {

    private lateinit var titleText: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var priorityPicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        titleText = findViewById(R.id.title)
        description = findViewById(R.id.text_view_description)
        priorityPicker = findViewById(R.id.number_picker_priority)

        priorityPicker.maxValue = 10
        priorityPicker.minValue = 1

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp)

        if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
            title = "Edit Note"
            titleText.setText(intent.getStringExtra(EXTRA_TITLE))
            description.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            priorityPicker.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
        } else {

            title = "Add Note"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    fun saveNote() {
        val noteTitle = titleText.text
        val noteDescription = description.text
        val notePriotiry = priorityPicker.value

        if (noteTitle?.trim()!!.isEmpty() || noteDescription?.trim()!!.isEmpty()) {
            Toast.makeText(this, "Enter title and description", Toast.LENGTH_LONG).show()
            return
        }

        val data = Intent()
        data.putExtra(EXTRA_TITLE, noteTitle)
        data.putExtra(EXTRA_DESCRIPTION, noteDescription)
        data.putExtra(EXTRA_PRIORITY, notePriotiry)

        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            data.putExtra(EXTRA_ID, id)
        }

        setResult(Activity.RESULT_OK, data)
        finish()

    }

    companion object {
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "EXTRA_PRIORITY"
        const val EXTRA_ID = "EXTRA_ID"
    }
}
