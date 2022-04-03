package com.dontsu.composenoteapppractice.di

import com.dontsu.composenoteapppractice.data.repository.NoteRepository
import com.dontsu.composenoteapppractice.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NoteModule {

    @Binds
    abstract fun provideNoteRepository(
        noteRepositoryImpl: NoteRepositoryImpl
    ): NoteRepository

}
