package com.example.dbtest

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dbtest.room.Note
import com.example.dbtest.room.NoteDatabase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_note.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class AddNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val db = NoteDatabase(this)
        newNoteBar.setNavigationOnClickListener { finish() }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            newNoteTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)))
        newNoteSave.setOnClickListener {
            try{
                if (newNoteBody.text.isNullOrEmpty())
                    MaterialAlertDialogBuilder(this).setTitle("Note Body cannot be empty")
                        .setPositiveButton("OK") { _, _ -> }.show()
                else {
                    if (newNoteTitle.text.isNullOrEmpty())
                        newNoteTitle.setText(newNoteBody.text?.take(10))
                    var note = Note()
                    note.title = newNoteTitle.text.toString()
                    note.body = newNoteBody.text.toString()
                    note.date = newNoteTime.text.toString()
                    db.noteDao().insertNote(note)
                    Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception){
                MaterialAlertDialogBuilder(this).setTitle("Note already exists with same body.")
                    .setPositiveButton("OK"){_,_->}.show()
            }
        }
    }
}