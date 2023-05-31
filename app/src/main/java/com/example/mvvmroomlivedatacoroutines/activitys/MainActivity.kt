package com.example.mvvmroomlivedatacoroutines.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmroomlivedatacoroutines.R
import com.example.mvvmroomlivedatacoroutines.adapter.NoteAdapter
import com.example.mvvmroomlivedatacoroutines.databinding.ActivityMainBinding
import com.example.mvvmroomlivedatacoroutines.model.Note
import com.example.mvvmroomlivedatacoroutines.repository.NoteRepository
import com.example.mvvmroomlivedatacoroutines.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
//    private val noteRepository: NoteRepository = NoteRepository(application)
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteviewmodelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initControls()
        initEvents()

    }

    private fun initControls() {
        val adapter: NoteAdapter =
            NoteAdapter(this@MainActivity, onClick = onItemClick, onDelete = onItemDelete)
            binding?.rvNote?.adapter=adapter
            noteViewModel.getAllNote().observe(this, Observer {
                adapter.setNotes(it)
            })
        Toast.makeText(this@MainActivity, "getAllNote", Toast.LENGTH_SHORT).show()
    }

    private val onItemClick: (Note) -> Unit = {
    var intent=Intent(this@MainActivity,UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE",it)
        startActivity(intent)
    }
    private val onItemDelete: (Note) -> Unit = {
        noteViewModel.deleteNote(it)
    }

    private fun initEvents() {
    binding?.btnOpenAddActivity?.setOnClickListener {
      val intent=Intent(this@MainActivity,AddNoteActivity::class.java)
        startActivity(intent)
    }
    }


}