package com.dontsu.composenoteapppractice.data

import com.dontsu.composenoteapppractice.data.entity.NoteEntity


/** 테스트 용으로만 사용했음 */
class NoteDataSource {

    fun loadNotes(): List<NoteEntity> {
        return listOf(
            NoteEntity(title = "A good day", description = "We went on a vacation by the lake"),
            NoteEntity(title = "Android Compose", description = "Working on Android Compose course today"),
            NoteEntity(title = "Keep at it...", description = "Sometimes things just happen"),
            NoteEntity(title = "A movie day", description = "Watching a movie with family today"),
            NoteEntity(title = "A movie day", description = "Watching a movie with family today"),
            NoteEntity(title = "A movie day", description = "Watching a movie with family today"),
            NoteEntity(title = "A movie day", description = "Watching a movie with family today"),
            NoteEntity(title = "A movie day", description = "Watching a movie with family today"),
            NoteEntity(title = "A movie day", description = "Watching a movie with family today"),
            NoteEntity(title = "A movie day", description = "Watching a movie with family")
        )
    }

}