package com.dontsu.composenoteapppractice.data.local

import androidx.compose.runtime.MutableState
import androidx.room.*
import com.dontsu.composenoteapppractice.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from notes_table")
    fun getAllNotes(): Flow<List<NoteEntity>> // 여기만 suspend를 안 붙이는 이유는? => Flow가 비동기처리를 지원하므로 가능. 또한 List<NoteEntity>로 받아오는 비동기적으로 처리하지만 데이터를 순차적으로 방출하기 위함

    @Query("SELECT * from notes_table WHERE id = :id")
    suspend fun getNoteById(id: String): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: NoteEntity)

    @Query("DELETE from notes_table")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note: NoteEntity)

}
