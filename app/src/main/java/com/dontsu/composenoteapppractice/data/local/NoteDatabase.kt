package com.dontsu.composenoteapppractice.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dontsu.composenoteapppractice.data.entity.NoteEntity
import com.dontsu.composenoteapppractice.util.DateTypeConverter
import com.dontsu.composenoteapppractice.util.UUIDTypeConverter

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
@TypeConverters(DateTypeConverter::class, UUIDTypeConverter::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao

}
