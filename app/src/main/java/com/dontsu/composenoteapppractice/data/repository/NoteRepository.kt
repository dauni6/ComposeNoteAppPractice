package com.dontsu.composenoteapppractice.data.repository

import com.dontsu.composenoteapppractice.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun addNote(note: NoteEntity)

    suspend fun updateNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)

    suspend fun deleteAllNotes()

    fun getAllNotes(): Flow<List<NoteEntity>>

}
