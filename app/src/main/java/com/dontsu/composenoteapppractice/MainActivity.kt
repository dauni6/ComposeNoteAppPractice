package com.dontsu.composenoteapppractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dontsu.composenoteapppractice.screen.NoteScreen
import com.dontsu.composenoteapppractice.screen.NoteViewModel
import com.dontsu.composenoteapppractice.ui.theme.ComposeNoteAppPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNoteAppPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    val notes = remember { // notes를 여기에 두면 오로지 MainActivity에서만 접근할 수 있다. 어떻게 이를 Hoisting할 수 있을까? => ViewModel로 만들어서 접근하기 ,viewModel을 쓰면 remember를 사용할 필요가 없음
//                        mutableStateListOf<Note>()
//                    }
                    NotesApp()
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val noteList = noteViewModel.noteList.collectAsState().value

    NoteScreen(
        noteList = noteList,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = { noteViewModel.removeNote(it) }
    )

}
