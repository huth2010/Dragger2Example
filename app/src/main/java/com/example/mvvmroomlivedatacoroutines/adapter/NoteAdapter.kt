package com.example.mvvmroomlivedatacoroutines.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmroomlivedatacoroutines.R
import com.example.mvvmroomlivedatacoroutines.databinding.NoteItemBinding
import com.example.mvvmroomlivedatacoroutines.model.Note

class NoteAdapter(
    private val context: Context,
    private val onClick: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var list: List<Note> = listOf()

    inner class NoteViewHolder(private val noteItemBinding: NoteItemBinding) :
        RecyclerView.ViewHolder(noteItemBinding.root) {
        fun bind(note: Note) {
            noteItemBinding.txtItemDes.text = note.description
            noteItemBinding.txtItemTitle.text = note.title
            noteItemBinding.btnDeleteNote.setOnClickListener {
                onDelete(note)
            }
            noteItemBinding.layoutItem.setOnClickListener {
                onClick(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var noteItemBinding: NoteItemBinding? = null
        noteItemBinding = NoteItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return NoteViewHolder(noteItemBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = list.get(position)
        holder.bind(note)
    }

    fun setNotes(notes: List<Note>) {
        this.list = notes
        notifyDataSetChanged()
    }


}