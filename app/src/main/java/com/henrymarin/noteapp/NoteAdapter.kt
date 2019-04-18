package com.henrymarin.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteHolder>(DiffCallback()) {

    private lateinit var itemClickListener: OnItemClickLister

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note, itemClickListener)
    }

    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }

    class NoteHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.text_view_title)
        var descriptionTextView: TextView = itemView.findViewById(R.id.text_view_description)
        var priorityTextView: TextView = itemView.findViewById(R.id.text_view_priority)

        fun bind(note: Note, clickLister: OnItemClickLister) {
            titleTextView.text = note.title
            descriptionTextView.text = note.description
            priorityTextView.text = note.priority.toString()
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    clickLister.onItemClick(note)
                }
            }
        }
    }

    interface OnItemClickLister {
        fun onItemClick(note: Note)
    }

    fun setOnItemClickListener(listener: OnItemClickLister) {
        itemClickListener = listener
    }

    class DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.description == newItem.description &&
                    oldItem.priority == newItem.priority
        }
    }
}