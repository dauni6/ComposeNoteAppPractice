package com.dontsu.composenoteapppractice.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dontsu.composenoteapppractice.data.entity.NoteEntity
import com.dontsu.composenoteapppractice.data.repository.NoteRepository
import com.dontsu.composenoteapppractice.di.IODispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    //    private var noteList = mutableStateListOf<NoteEntity>()
    private val _noteList = MutableStateFlow<List<NoteEntity>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        // noteList.addAll(NoteDataSource().loadNotes()) 이젠 필요없음
        viewModelScope.launch(ioDispatcher) {
            repository.getAllNotes().distinctUntilChanged()
                .collect { listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) {
                        _noteList.value = emptyList()
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
    }

    fun addNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.addNote(note = noteEntity)
    }

    fun updateNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.updateNote(note = noteEntity)
    }

    fun removeNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.deleteNote(note = noteEntity)
    }

}
