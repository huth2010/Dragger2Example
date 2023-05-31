package com.example.mvvmroomlivedatacoroutines.activitys

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmroomlivedatacoroutines.R
import com.example.mvvmroomlivedatacoroutines.databinding.ActivityAddNoteBinding
import com.example.mvvmroomlivedatacoroutines.model.Note
import com.example.mvvmroomlivedatacoroutines.viewmodel.NoteViewModel

class AddNoteActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteviewmodelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    private var binding:ActivityAddNoteBinding?=null
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnAdd?.setOnClickListener {
        val note=Note(binding?.edtNoteTitle?.text.toString(),binding?.edtNoteDes?.text.toString())
            noteViewModel.insertNote(note)
            finish()
        }
    }
}