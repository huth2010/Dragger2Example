package com.example.mvvmroomlivedatacoroutines.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmroomlivedatacoroutines.R
import com.example.mvvmroomlivedatacoroutines.databinding.ActivityUpdateNoteBinding
import com.example.mvvmroomlivedatacoroutines.model.Note
import com.example.mvvmroomlivedatacoroutines.viewmodel.NoteViewModel

class UpdateNoteActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteviewmodelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    var binding: ActivityUpdateNoteBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val note=intent.getSerializableExtra("UPDATE_NOTE") as Note
        binding?.edtNoteTitle?.setText(note.title)
        binding?.edtNoteDes?.setText(note.description)
        binding?.btnUpdate?.setOnClickListener {
            note.title=binding?.edtNoteTitle?.text.toString()
            note.description=binding?.edtNoteDes?.text.toString()
            noteViewModel.updateNote(note)
            finish()
        }
    }
}