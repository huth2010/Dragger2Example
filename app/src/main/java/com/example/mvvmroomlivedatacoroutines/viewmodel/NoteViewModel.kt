package com.example.mvvmroomlivedatacoroutines.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvmroomlivedatacoroutines.database.dao.NoteDao
import com.example.mvvmroomlivedatacoroutines.model.Note
import com.example.mvvmroomlivedatacoroutines.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NoteViewModel(application: Application):ViewModel() {

    private val noteRepository:NoteRepository= NoteRepository(application)

    fun insertNote(note:Note)=viewModelScope.launch {
        noteRepository.insertNote(note)

    }

    fun updateNote(note: Note)=viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note)=viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAllNote():LiveData<List<Note>> = noteRepository.getAllNote()

    class NoteviewmodelFactory(private val application: Application):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
                return NoteViewModel(application) as T
            }
            throw IllegalArgumentException("unable construct viewmodel")
        }

    }
}