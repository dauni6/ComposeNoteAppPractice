package com.dontsu.composenoteapppractice.data.repository

import com.dontsu.composenoteapppractice.data.entity.NoteEntity
import com.dontsu.composenoteapppractice.data.local.NoteDatabaseDao
import com.dontsu.composenoteapppractice.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDatabaseDao: NoteDatabaseDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): NoteRepository {

    override suspend fun addNote(note: NoteEntity) = noteDatabaseDao.insertNote(note = note)

    override suspend fun updateNote(note: NoteEntity) = noteDatabaseDao.updateNote(note = note)

    override suspend fun deleteNote(note: NoteEntity) = noteDatabaseDao.deleteNote(note = note)

    override suspend fun deleteAllNotes() = noteDatabaseDao.deleteAllNotes()

    override fun getAllNotes(): Flow<List<NoteEntity>> = noteDatabaseDao.getAllNotes().flowOn(ioDispatcher).conflate()

}
