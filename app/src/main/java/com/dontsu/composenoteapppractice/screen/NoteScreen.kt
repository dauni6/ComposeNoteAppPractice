package com.dontsu.composenoteapppractice.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dontsu.composenoteapppractice.R
import com.dontsu.composenoteapppractice.components.NoteButton
import com.dontsu.composenoteapppractice.components.NoteInputText
import com.dontsu.composenoteapppractice.data.entity.NoteEntity
import com.dontsu.composenoteapppractice.util.toDate

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    noteList: List<NoteEntity>,
    onAddNote: (NoteEntity) -> Unit,
    onRemoveNote: (NoteEntity) -> Unit
) {
    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(6.dp)
    ) {
        // TopBar
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Action Icon"
                )
            },
            backgroundColor = Color(0xffdadfe3)
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() or char.isWhitespace()
                        }) title = it
                },
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp)
            )
            NoteInputText(
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() or char.isWhitespace()
                        }) description = it
                },
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp)
            )

            NoteButton(
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        // save/add to the list
                        onAddNote(NoteEntity(title = title, description = description))
                        // reset title and description.
                        Toast.makeText(context, "Note Added.", Toast.LENGTH_SHORT).show()
                        title = ""
                        description = ""
                    }
                }
            )
        }

        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(noteList) { note ->
                NoteRow(
                    noteEntity = note,
                    onNoteClicked = {
                        onRemoveNote(note)
                    }
                )
            }
        }

    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    noteEntity: NoteEntity,
    onNoteClicked: (NoteEntity) -> Unit
) {
    val context = LocalContext.current

    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp
    ) {
        Column(
            modifier = modifier
                .clickable {
                    onNoteClicked(noteEntity)
                    Toast
                        .makeText(context, "Note Removed.", Toast.LENGTH_SHORT)
                        .show()
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = noteEntity.title,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = noteEntity.description,
                style = MaterialTheme.typography.subtitle1,
            )
            Text(
                text = noteEntity.entryDate.time.toDate(), // ex) Jul 18, Wed, Jul 23 1999...
                style = MaterialTheme.typography.caption,
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(noteList = emptyList(), onAddNote = {}, onRemoveNote = {})
}
